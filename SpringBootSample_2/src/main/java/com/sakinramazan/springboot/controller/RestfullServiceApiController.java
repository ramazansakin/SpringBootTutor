package com.sakinramazan.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sakinramazan.springboot.model.User;
import com.sakinramazan.springboot.service.UserService;
import com.sakinramazan.springboot.util.ErrorMessage;

// RestController annotation'ı  adında anlayabileceğimiz gibi Restfull service yazacağımız zaman
// Service class ının basına getiririz. Ve bu service in default ResponseBody sinin JSON olduğu belirtmiş oluruz
// view ile Html(JSP, JSF) , Xml gibi datalar dönemeyiz. Arayüz ile alakalı bir olay yoktur sadece database den aldığımız 
// ya da bu projede örnek olarak static datalar ile haberleşmeyi sağlarız.
@RestController
// Service den gelen requestler için service Context path i /servicebasepath/...
// şeklindeki path lerle method controller
// tarafından yönlendirilir.(dispatch edilir)
@RequestMapping("/servicebasepath")
public class RestfullServiceApiController {

	// Herhangi bir problemi görmek ve ya akışı daha iyi takip edebilmek adına
	// logger kullanırız.
	// Burda Simple Logging Facade for Java (SLF4J) java.util.logging, logback,
	// log4j gibi java loglama
	// library lerini abstract ederek(Facade pattern) bize istediğimizi rahatça
	// kullanabilme imkanı sağlamış.
	public static final Logger logger = LoggerFactory.getLogger(RestfullServiceApiController.class);

	// Bir service interface nin hangi service bağlanacağını belirlemede kullanırız.
	// Auto wired : Otomatik olarak bağlanan şeklinde b,r çeviri yaptığımızda da bir
	// nebze anlasılıyor :)
	@Autowired
	UserService userService; // User uzerinde yapacagimiz islemler icin kullanilacak servis

	// Tüm kullanıcı ları getir
	// SuppressWarnings annotation ı ; bazı istemediğimiz warning leri bastırmak
	// için kullanılır :D
	// bastırmak derken gizlemek manasında, IDE miz görmezden gelir(ignore edilir)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();

		if (users.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// Verilen id ile eşleşen kullanıcı i getir
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("id ile gelen parthParam dan gerekli datayı alıyoruz : {}", id);
		User user = userService.findById(id);

		if (user == null) {
			logger.error("Bu id {} ile eşleşen bir kayıt bulunmamaktadır.", id);
			return new ResponseEntity<Object>(new ErrorMessage("id " + id + " kayıtlı değil!"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Yeni bir kullanıcı tanımla ve kaydet
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Yeni User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("KAyıt başarısız. Bu id {} ile bir kullanıcı mevcut", user.getName());
			return new ResponseEntity<Object>(
					new ErrorMessage("Kayıt yapılmamadı!. Bu id de  " + user.getName() + " kullanıcısı mevcuttur.."),
					HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// Verilen id ile eşleşen kullanıcı i update eder
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Kullanıcıyı güncelle : {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Güncelleme başarısız. Bu id {} eşleşen kayıt bulunamadı.", id);
			return new ResponseEntity<Object>(
					new ErrorMessage("Güncelleme başarısız. Bu id : " + id + " ait kullanıcı bulunmamaktadır."), HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// Verilen id ile eşleşen kullanıcı yı siler
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Kullanıcı Silme : Gelen id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Kullanıcı bulunamadı!", id);
			return new ResponseEntity<Object>(
					new ErrorMessage("Silme başarısız. id " + id + " ile herhangi eşleşen bir kayıt bulunamadı."), HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// Tüm kullanıcı ları siler
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Tüm kullanıcıları sil!");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}