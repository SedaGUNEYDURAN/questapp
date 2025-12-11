## Annotation

- **``` @SpringBootApplication:```** Uygulamanın giriş noktrası olduğunu belirtir. Üç önemli özelliği etkinleştirir; auto-configuration, component scanning, configuration.    


   • **@EnableAutoConfiguraion:** Spring Boot'un otomatik yapılandırma mekanizmasını açar. Projenin bağımlılıklarına göre gerekli beanleri otomatik olarak oluşturur.   
   • **@ComponentScan:** Uygulamanın bulunduğu paketten başlayarak alt paketlerdeki @Component, @Service, @Repository, @Controller gibi classları tarar ve Spring context'e ekler.  
   • **@SpringBootConfiguration:** Bu classın bir konfigürasyon classı olduğunu belirtir. Yani bu bean tanımları yapabileceğimiz anlamına gelir.   

- **``` @Entity```**: Java classını, JPA(Java Persistence API) ile veritabanındaki bir tabloya eşleştirmek için kullanılır. Yani **sınıf bir tabloya, her alan bir sütun, her nesne tablodaki her bir satır karşılık gelir**. ORM(Object Relational Mapping) sağlar. Yani Java nesnelerini SQL tablolarına bağlar. Veriler üzerinde yapılan işlemler(save, delete,update) otomatik olarak SQL sorgularına dönüşür. **Her @Entity sınıfında bir @Id alanı olmak zorundadır. Bu, tablonun primary keyidir. **    

  • **@Tablo(name="...")**: Tablo adı class asından farklı olacaksa eklenmeli  
  • **@Column("name=...")**: Alanların sütun isimlerini özelleştirmek için eklenmelidir.

- **``` @Data```**: Project Lombok kütüphanesinin sağladığı bir anotasyondur. Bir sınıfın üzerine yazıldığında, o sınıf için otomatik olarak aşağıdaki metotları oluşturur;(normalde elle yazmamız gereken birçok kodu Lombokk böylece derleme aşamasında ekler.)   

  • **@Getter**:tüm alanlar için getter metotları    
  • **@Setter**:tüm alanlar için setter metotları    
  • **@ToString**: anlamlı bir toString() metodu   
  • **@EqualsAndHashCode**: equals() ve hashCode() metotları   
  • **@RequiredArgsConstructor**: final alanlar için constructor

  **Bu anotasyon tüm getter/setterları eklediği için immutable(değişmez) classlar için uygun değildir.**   

- **@Lob**: (Large Object) JPA anotasyonudur. Bir entity classındaki alanın büyük boyutlu veri olarak saklanacağını belirtir. Veritabanında bu alan CLOB(Character Large Object) veya BLOB(Binary Large Object) tipine karşılık gelir.
- **```RestController```**:bir classın Restful web servislerini (API) sunacak bir bileşen olduğunu belirtir.Yani o classı, HTTP isteklerini karşılayan bir API katmanı haline getiriyoruz. İki temel spring annotationın birleşimidir.   
     • **@Controller**: Spring'e bu sınıfın gelen http isteklerini işlemekten sorumlu bir bileşen olduğunu söyler. Bu classların içindewki metotlarda @GetMapping, @PostMapping gibi annotationlar ile birlikte URL'lere eşlenir.        
    - **@GetMapping**: Controller classının içindeki bir metodun client tarafından bir adrese(URL) veri isteme talebi gönderdiğinde çalışması gerektiğini Spring'e bildirir.    
    - **@PostMapping**: Spring'e bu metodun belirli bir URL'e yapılan HTTP POST isteğine yanıt vermesi gerektiğini söyler. Bir Controller metodunun istemciden gelen verileri kabul edip yeni bir kaynak(resource) oluişturmak için kullanılan HTTP POST isteklerini işleyeceğini belirtir.
    - **@PutMapping**: Bir resourcedaki mevcut veriyi güncellemeyi amaçlayan HTTP isteklerini belirli bir controller metoduna yönlendirir.      
 ```java
@PutMapping("/{userId}") // PUT isteği /users/{userId} adresine eşlenir
 ```

   - **@DeleteMapping**: Belitilen adresteki kaynağı silmeyi amaçlayan HTTP isteklerini belirli bir Controller metoduna yönlendirir.
   - **@RequestBody**: Controller metot parametresine konulan bir anotasyondur. Spring'e gelen HTTP isteğinin gövdesindeki veriyi Java nesnesine dönüştürür. Clienttan gelen JSON,XML... verisini otomatik olarak karşılık gelen Java classına map eder.
   - **@PathVariable**: URL içerisinde yer alan değişken parçaları yakalamak ve bu değerleri metot parametrelerine aktarmak için kullanılır. URL içindeki {} içindeki dinamik değeri çeker.   

 ```java
  @GetMapping("/{userId}")
  public User getOneUser(@PathVariable Long userId) { 
		return userService.getOneUser(userId);
	}
```      

   - **@RequestParam**: Bu anotasyon Controller classındaki metot parametresine konulduğunda Spring'e gelen URL'indeki sorgu dizesini okumasını söyler. Diyelim ki URL şöyle bir şey olsun; /posts?page=2&size=10&userId=5 .Burada URL'deki soru işaretinden sonra gelen kısım sorgu dizesidir -> page=2, size=10. Bu anotasyon, bu sorgu parametrelerinin değerlerini  yakalar. Yakalan  değeri, metot parametresine otomatik olarak atar. Yakalan String değeri , metot parametresinde bulunan türe otomatik olarak dönüştürür.   
    
     •**@ResponseBody** : @Controller bir String döndürdüğünde, Spring bu String'i bier view adı (JSP, HTML) olarak yorumlar. Ama bu anotasyon eklendiğinde; Spring metotdan dönen nesneyi(genellikle java objesi) alır ve bunu doğrudan HTTP yanıt gövdesine(response body'ye) yazar. Spring Boot, bu işlemi defauşlt olarak JSON formatına dönüştürerek serialization yapar. Kısaca **Java objesini API aracılığı ile dış dünyaya JSON metni olarak sunar.**

-  **@RequestMapping**: Bir Controller classını veya bir Controller metodunu belirli bir URL'e eşlemektedir.    
    
- **@ManyToOne**: İki entity arasındaki ilişkinin many to one olduğunu belirtir. Parametre olacak  fetch=FetchType.LAZY alabi,lir. Peki bu ne demektir? JPA/Hibernate'e bu ilişkinin verileri ne zaman veritabanından alınacağını söyler. Lazy, tembel yüklemedir yani bir entityi yüklediğimizde onunla ilişkili entitynin hemen yüklenmeyeceğini ifade eder. Bu EAGER olsaydı -ki eğer LAZY diye belirtmezsek JPA defaultu EAGER'dır- ana entity yüklenir yüklenmez ilişkili entity aynı anda ya da JOIN bir sorguyla otomatik olarak yüklenir. Lazy yükjleme başlangıç performansını arttırsa da, eğer listedeki her bir ögenin ilişkili verisine döngü içinde erişirsek n+1 sorgu sorununa yol açar. Bier tane ana sorgumuz var ve diyelim ki ilişkili de 100 tane veri var. Her 100 öge için bir sorgu atacağımız için toplamda 101 sorgu olur. Ama bununda JOIN FETCH ile önüüne geçebiliyoruz.     
- **@JoinColumn**:JPA ve Hibernate gibi ORM araçlarını kullanırken veritabanı ilişkilerini(manytomany,onetoone...) yönetmek için kullanılan bir anotasyondur.İki entity arasındaki ilişkinin hangi column üzerinden kurulacağını söyler. **unique** olarak belirtilmişse    bu sütunun değerleri unique'dir. Özellikle @OneToOne ilişkilerde zorunludur veya true yapılmalıdır. **nullable** ise foreign key sütununun veritabanında null değer alıp almayacağını belirler, default değeri true'dur.    
- **@OnDelete**: silme işlemi sırasında veritabanı seviyesinde temizlik yapmayı sağlar. Ana kayıt silinirse, ona bağlı olanları da veritabanı otomatik olarak silsin emridir.     
   •  **cascade=CascadeType.REMOVE**, java seviyesinde silmedir. Yazarı sil dediğimizde hibernate önce veritabanına gider, o yazarın tüm kitaplarını SELECT sorgusu ile tek tek bulur. Sonra her kitap için ayrı ayrı DELETE komutu gönderir. En son da yazarı siler. Çok fazla SQL sorgusu çalışır ve performans düşebilir.    
  •  **@OnDelete(action=OnDeleteAction.CASCADE)**, veritabanı seviyesinde silmedir. Hibernate veritabanına tek  bir komut gönderir; yazarı sil. Veritabanı kendi içinde constraintlere bakarak kitapları kendisi temizler. Çok daha hızlıdır.

- **@JsonIgnore**: Bu alanı JSON çıktısına dahil etme yani serileştirme ve JSON girdisinden bu alanı okumaya çalışma yani deserilialization yapma.
- **@Service**: bir classı, business logic service olarak işaretlemek için kullanılan özelleştirilmiş @Component anotasyonudur. Bu anotasyon sayesinde, Spring'in IoC Container'ı bu sınıfı otomatik olarak bulur, bean olarak oluşturur ve uygulamanın yaşam döngüsü boyunca yönetir. Bu anotasyon ile işaretlenmiş olan class Controller katmanı ile Repository(Veri erişim) katmanı arasında köprü görevi görür.    

   
  ## JPA
 - JpaRepository extend edildiğinde, Spring bizim için hazır CRUD metotlarını sağlar. Yani kendi save(), findByID(), delete() gibi metotları yazmamıza gerek kalmaz.

   • save(entity): insert/update   
   • findById(id): tek kayıt bulma   
   • findAll(): tüm kayıtarı listeleme   
   • delete(entity): silme   
   • count(): toplam satır sayısı   
   • findAll(Pageable pageable): sayfalama   
   • findAll(Sort sort):sıralama   

  Spring Data JPA'de repository oluştururken şu şekilde yazılır;   

 ```java
  
  public interface UserRepository extends JpaRepository<User, Long> {   
  }

 ```

  User; entity sınıfıdır, tabloya karşılık gelen class  
  Long olarak belirtilen id tipiir. Entity'in primary key'idir.      
  Spring Boot uygulaması açıldığında, @Repository anotasyonu gerekmeden Spring bu interface'i otomatik olarak bean olarak tanır. Burada olduğu gibi biz sadece interface tanımlıyoruz. Spring Boot arkada SQL sorgularını ve implementasyonlarını otomatik oluşturuyor.    
 
 - JPA'in en güçlü yanlarından biri de dinamin sorgu yaratmadır(Dynamic Query Generation). Biz metodu tanımlıyoruz ve Spring Data JPA bizim yerimize arkaplanda SQL komutunu üretiyor. Spring Data JPA, Repository(buradaki interfacelerimizin adı) interfaceindeki metot isimlerini okur ve bu isimleri bir dil gibi yorumlar ve buna göre sorguı oluşturur. Diyelim ki metodumuz ;List< Comment > findByUserIdAndPostId(Long userId, Long postId);    
 	- find ve get gördüğünde SELECT ile bir sorgu başlatacağını anlar.   
 	- By gördüğünde sorgunun WHERE koşulunun başladığı yer olarak anlar.   
  	- userId gördüğünde by'dan sonra bunu şu şekilde birleştirir; WHERE  Comment.userId=?   
    - and gördüğünde ikinci bir koşulun eklendiğini anlar.   
    - postId gördüğünde  AND Comment.postId=? anlar.   
Sonuçta üretilen sorgu ; SELECT * FROM COMMENT WHERE user_id = [userId'in değeri] AND post_id = [postId'nin değeri];    
Spring bu sorguyu çalıştırır ve sonuçlarını otomatik olarak bir List<Comment> objesine dönüştürecek dinamik bir sınıf yani proxy class oluşturur ve onu CommentRepository'nin yerine koyar. Controller veya Service katmanı sanki bu metodu yazmışız gibi çağırır, arka planda Spring'in oluşturduğu SQL komutu çalışır.    

  ## React
-  Node.js kurulumunu yaptıktan sonra Visual Studio Code terminalinde 

 ```java
npx create-react-app projectName
 ```
   diyoruz ve projemiz oluşturuluyor. cd projectName diyoruz ve proje dizinimize geçiyoruz. 
   
```java
npm start
```
     
dediğimizde de proje ayağa kalkar. 
