# Spring Boot, Hibernate ve MySQL Sample Project

Spring Boot, Hibernate ve MySQL teknolojilerini kullanarak eventlerimizi tutabileceğimiz restfull mimarisi ile
geliştirilmiş bir projedir.

## Gerekenler

1. Java  - 1.8+
2. Maven - 3.+
3. Mysql - 5.+

## Database için yapılacaklar

  MySQL ile hiç uğraşmadıysanız https://www.wikihow.com/Install-XAMPP-for-Windows buradan kurulumu gerçekleştirerek 
http://localhost/phpmyadmin adresinden Database e erişim sağarız. Buradan Yeni diyerek 'event' adında bir database 
oluşturarak. Events adında bir tablo oluşturup buraya model class ımızda da gördüğümüz fieldları adı @Column annotationdaki
isimleri aynı oalcak şekilde tablomuza alanlar ekleyerek database kısmıı tamamlamış oluruz.


## Kullandığımız Service methodları (Api lerimiz)

    GET /api/events
    POST /api/events
    GET /api/events/{eventId}
    PUT /api/events/{eventId}
    DELETE /api/events/{eventId}

Postman , SoapUI ve ya farklı bir şeklide client generate ederek api leri test edebilirsiniz.

