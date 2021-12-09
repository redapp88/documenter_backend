package net.letapp.documenter.services;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.letapp.documenter.dao.CheckingRespository;
import net.letapp.documenter.dao.DocumentRepository;
import net.letapp.documenter.domaine.DocumentPageVo;
import net.letapp.documenter.domaine.DocumentVo;
import net.letapp.documenter.domaine.MonthChecksVo;
import net.letapp.documenter.domaine.converters.DocumentConverter;
import net.letapp.documenter.domaine.converters.DocumentTypeConverter;
import net.letapp.documenter.entities.Checking;
import net.letapp.documenter.entities.Document;
import net.letapp.documenter.entities.Folder;

@Service
public class DocumentServiceImp implements DocumentService {
	@Autowired
	private DocumentRepository DocumentRepository;
	@Autowired
	private FolderService folderService;
	@Autowired
	private CheckingRespository checkingRespository;
	@Autowired
	private Environment environment;

	@Override
	public DocumentVo add(DocumentVo vo) {
		Folder folder = this.folderService.get(vo.getFolder().getId());
		if (folder == null)
			throw new RuntimeException("Folder not found");
		folder.getDocuments().forEach(document -> {
			if (folder.getName().equals(vo.getTitle())) {
				throw new RuntimeException(this.environment.getProperty("errors.documents.error1"));
			}
		});

		return DocumentConverter.toVo(this.DocumentRepository.save(DocumentConverter.toBo(vo)));

	}

	@Override
	public DocumentPageVo getAll(Long folderId, String keyword, int page, int size) {
		if (keyword.equals("*"))
			keyword = "";
		keyword = "%" + keyword + "%";

		Page<Document> loadedpage = this.DocumentRepository.findByKeyword(folderId, keyword,
				PageRequest.of(page, size, Direction.DESC, "creatDate"));
		DocumentPageVo documentPageVo = new DocumentPageVo();
		documentPageVo.setContent(DocumentConverter.toListVo(loadedpage.getContent()));
		documentPageVo.setNumberOfElements(loadedpage.getTotalElements());
		documentPageVo.setPageSize(loadedpage.getSize());
		return documentPageVo;
	}

	@Override
	public Document get(String id) {
		Optional<Document> opt = this.DocumentRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	@Override
	public void delete(List<String> ids) {
		ids.forEach(id -> {
			Document document = this.get(id);
			if (document == null)
				throw new RuntimeException(this.environment.getProperty("errors.documents.error2"));
		});
		this.DocumentRepository.deleteAllById(ids);

	}

	@Override
	public DocumentVo edit(String id, DocumentVo vo) {
		Document document = this.get(id);
		if (document == null)
			throw new RuntimeException(this.environment.getProperty("errors.documents.error2"));
		document.setTitle(vo.getTitle());
		document.setDescription(vo.getDescription());
		document.setDocmentType(DocumentTypeConverter.toBo(vo.getDocumentType()));
		return DocumentConverter.toVo(DocumentRepository.save(document));
	}

	@Override
	public List<DocumentVo> getTopChecks(String username) {
		int top = Integer.parseInt(environment.getProperty("my.topDocuments"));
		List<DocumentVo> documentsVo = DocumentConverter.toListVo(DocumentRepository.getTopChecks(username));
		return documentsVo.stream().limit(top).collect(Collectors.toList());
	}

	@Override
	public List<MonthChecksVo> yearChecks(String username) {
		List<MonthChecksVo> MonthChecksList = new ArrayList<MonthChecksVo>();
		LocalDate now = LocalDate.now();
		for (int i = 0; i < 12; i++) {
			LocalDate earlier = now.minusMonths(i);
			Integer month = earlier.getMonthValue();
			Integer year = earlier.getYear();
			Long countChecks = new Long(checkingRespository.getChecksMonth(username,month,year).size());
			MonthChecksList.add(new MonthChecksVo(year.toString(), month.toString(), countChecks) );
		}
		Collections.reverse(MonthChecksList);
		return MonthChecksList;

	}

	@Override
	public DocumentVo checkDocument(String documentId,HttpServletRequest request){
	Document document=this.get(documentId);
	if(document==null)
		throw new RuntimeException(this.environment.getProperty("errors.documents.error2"));
	Checking checking = new Checking();
	checking.setDate(new Date());
	checking.setDocument(document);
	checking.setVisitor(request.getRemoteAddr());
	checking.setDetails(request.getPathInfo());
	
	checking=checkingRespository.save(checking);
	document.getChecks().add(checking);
	return DocumentConverter.toVo(document);
	}



}
