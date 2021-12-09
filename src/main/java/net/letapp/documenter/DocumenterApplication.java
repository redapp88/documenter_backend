package net.letapp.documenter;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import net.letapp.documenter.dao.AppRoleRepository;
import net.letapp.documenter.domaine.CategoryVo;
import net.letapp.documenter.domaine.DocumentTypeVo;
import net.letapp.documenter.entities.AppRole;
import net.letapp.documenter.services.CategoryService;
import net.letapp.documenter.services.DocumentTypeService;

@SpringBootApplication
public class DocumenterApplication implements CommandLineRunner {
@Autowired
private AppRoleRepository appRoleRepository;
@Autowired
private CategoryService categoryService;
@Autowired 
DocumentTypeService documentTypeService;
	public static void main(String[] args) {
		
		SpringApplication.run(DocumenterApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * AppRole role1=new AppRole(null,"USER"); AppRole role2=new
		 * AppRole(null,"MANAGER"); this.appRoleRepository.save(role1);
		 * this.appRoleRepository.save(role2);
		 * 
		 * this.categoryService.add(new CategoryVo(null,"School"));
		 * this.categoryService.add(new CategoryVo(null,"Univeristy"));
		 * 
		 * 
		 * documentTypeService.add(new DocumentTypeVo(null,"bill"));
		 * documentTypeService.add(new DocumentTypeVo(null,"Diplome"));
		 */
		 
			

	
		
		
	}

}
