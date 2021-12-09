package net.letapp.documenter.services;

import java.util.List;

import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.domaine.ImageVo;
import net.letapp.documenter.domaine.ImagesContainerVo;

public interface ImageService {

	void saveAll(ImagesContainerVo imagesContainer) throws Exception;

	public List<ImageVo> getAllByDocument(String documentId);

	public void editImages(String id, ImagesContainerVo imagesContainer);

}
