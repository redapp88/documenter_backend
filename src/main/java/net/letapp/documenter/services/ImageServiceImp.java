package net.letapp.documenter.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import net.letapp.documenter.dao.DocumentRepository;
import net.letapp.documenter.dao.ImageRepository;
import net.letapp.documenter.domaine.ImageVo;
import net.letapp.documenter.domaine.ImagesContainerVo;
import net.letapp.documenter.domaine.converters.ImageConverter;
import net.letapp.documenter.entities.Document;
import net.letapp.documenter.entities.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Transactional
public class ImageServiceImp implements ImageService {
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private Environment environment;

	@Override
	public void saveAll(ImagesContainerVo imagesContainer) throws Exception {
		Document document = this.documentService.get(imagesContainer.getDocumentId());
		if (document == null)
			throw new RuntimeException(this.environment.getProperty("errors.documents.error2"));
		List<Image> images = ImageConverter.toListBo(imagesContainer.getImages());

		BufferedImage bareCode = this.generateQR(imagesContainer.getDocumentId());

		images.forEach(el -> {
		
			/*
			 * byte[] decodedBytes = Base64.getMimeDecoder().decode(el.getUrl());
			 * InputStream is = new ByteArrayInputStream(decodedBytes);
			 */
			String base64Image = el.getUrl().split(",")[1];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			try {
				BufferedImage buffredOriginalImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
				//System.out.println("**************************" + buffredOriginalImg);

				Graphics2D g = buffredOriginalImg.createGraphics();
				g.setFont(new Font("TimesRoman", Font.BOLD, 40));
				g.setColor(Color.BLACK);
				g.drawString("documenter ID : "+imagesContainer.getDocumentId(), 250, 100);
				if (bareCode != null)
					g.drawImage(bareCode, null, 50, 50);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(buffredOriginalImg, "jpg", bos);
				byte[] imageBytesOut = bos.toByteArray();

				Base64.Encoder encoder = Base64.getEncoder();
				el.setUrl(encoder.encodeToString(imageBytesOut));
				bos.close();
			} catch (IOException e) {
				throw new RuntimeException("upload error");
			}

			el.setDocument(document);
		});
		this.imageRepository.saveAll(images);

	}

	@Override
	public List<ImageVo> getAllByDocument(String documentId) {
		// System.out.println(imageRepository.findAllByDocumentId(documentId));
		return ImageConverter.toListVo(imageRepository.findAllByDocumentId(documentId));
	}

	@Override
	public void editImages(String id, ImagesContainerVo imagesContainer) {

		Document document = this.documentService.get(imagesContainer.getDocumentId());

		if (document == null)
			throw new RuntimeException(this.environment.getProperty("errors.documents.error2"));
		List<Image> oldImages = document.getImages();
		List<Image> newImages = new ArrayList<Image>();
		imagesContainer.getImages().forEach(el -> {
			Image img = new Image();
			img.setId(el.getId());
			if (el.getId() == null) {
				System.out.println("new one " + el.getId());
				img = ImageConverter.toBo(el);
				img.setDocument(document);
				img.setOrdre(el.getOrdre());
				newImages.add(img);
			} else if (oldImages.indexOf(img) != -1) {
				System.out.println("old one " + el.getId());
				img = this.imageRepository.getById(img.getId());
				img.setOrdre(el.getOrdre());
				newImages.add(img);
			}
		});

		this.imageRepository.deleteAll(oldImages);
		// this.documentRepository.save(document);
		this.imageRepository.saveAll(newImages);

	}

	private BufferedImage generateQR(String barcodeText) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

}
