package com.sakinramazan.eventpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sakinramazan.eventpro.model.Event;

// Persistency(Data ların kalıcılığı) nin sağlanacağı fonksiyonalitenin olduğu ve yönetildiği
// class ı ifade etmek için bu annotation kullanılır.
@Repository
// Burada sadece extend edilmiş ve herhangi bir override falan yapmadık ; çünkü buradaki springframework nün bize 
// sağlamış olduğu JpaRepository interface inde bize gerekli olan CRUD(Create-Read-Update-Delete) operasyonları 
// mevcuttur ve diğer güzel özelliği de interfacedir ama autowire ederek direk bir instance ı üzerinden
// işlemlerimizi yapabiliriz. Böylede güzel bir kolaylık sağlamıştır. Ama istersek ki ileri için isteyeceğiz ;
// database deki business logic i burada yapmak için farklı Repository katmanları ile çalışarak datayı dönmemiz gerekecek ve 
// burada işler karışmaya başlayacak. Ama problem yok :) Repository lerimiz herbirisi için ayrı ayrı ve işlemlerimizi
// modüler bir şekilde geliştirisek bunların beraber çalışması da o kadar kolay olacaktır.
public interface EventRepository extends JpaRepository<Event, Long> {

}
