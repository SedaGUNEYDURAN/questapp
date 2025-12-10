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
    
     • **@ResponseBody**: @Controller bir String döndürdüğünde, Spring bu String'i bier view adı (JSP, HTML) olarak yorumlar. Ama bu anotasyon eklendiğinde; Spring metotdan dönen nesneyi(genellikle java objesi) alır ve bunu doğrudan HTTP yanıt gövdesine(response body'ye) yazar. Spring Boot, bu işlemi defauşlt olarak JSON formatına dönüştürerek serialization yapar. Kısaca **Java objesini API aracılığı ile dış dünyaya JSON metni olarak sunar.**
    
- **@JoinColumn**:JPA ve Hibernate gibi ORM araçlarını kullanırken veritabanı ilişkilerini(manytomany,onetoone...) yönetmek için kullanılan bir anotasyondur.İki entity arasındaki ilişkinin hangi column üzerinden kurulacağını söyler.    

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
 
 - 

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
