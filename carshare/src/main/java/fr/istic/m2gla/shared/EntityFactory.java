package fr.istic.m2gla.shared;


import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface EntityFactory extends AutoBeanFactory {
    AutoBean<IPerson> user();

//    AutoBean<IEvent> event();
//
//    AutoBean<IAvis> avis();
//
//    AutoBean<IPreference> pref();
//
//    AutoBean<IVille> ville();

}
