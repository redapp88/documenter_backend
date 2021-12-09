package net.letapp.documenter.web;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.letapp.documenter.domaine.ImageVo;
import net.letapp.documenter.domaine.ImagesContainerVo;
import net.letapp.documenter.services.ImageService;


@RestController
@RequestMapping("/images")
public class ImagesController {
	@Autowired
	ImageService imageService;
	@PostMapping
	public void addImages(@RequestBody ImagesContainerVo imagesContainer ) {
	try {
		this.imageService.saveAll(imagesContainer);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@PutMapping(value="{id}")
	public void editImages(@PathVariable String id,@RequestBody ImagesContainerVo imagesContainer){
		this.imageService.editImages(id,imagesContainer);

	}

	@GetMapping()
	public List<ImageVo> imagesByDocument(@RequestParam String documentId){
		System.out.println(documentId);
		List<ImageVo> images = this.imageService.getAllByDocument(documentId);
		 return images;
	}
	

	 
}
