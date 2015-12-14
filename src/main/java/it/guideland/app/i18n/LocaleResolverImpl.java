package it.guideland.app.i18n;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import it.guideland.app.model.SupportedLanguage;
import it.guideland.app.services.SupportedLanguageService;



public class LocaleResolverImpl extends AcceptHeaderLocaleResolver{
	
	@Autowired
	SupportedLanguageService supportedLanguageService;
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		
		Locale resolved = super.resolveLocale(request);
		resolved.setDefault(Locale.ENGLISH);
		if(request.getHeader("lang") != null){
			resolved = new Locale(request.getHeader("lang"));
		}
		
    	List<SupportedLanguage> langs = supportedLanguageService.findAllSupportedLanguages();
		boolean supported = false;
		for (SupportedLanguage lang : langs) {
			if(resolved.equals(new Locale(lang.getLangId()))){
				supported = true;
				break;
			}
		}
		if(!supported){
			return Locale.ENGLISH;
		}
	
		return resolved;
	}

}
