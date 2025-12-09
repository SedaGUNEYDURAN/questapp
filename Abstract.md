## Annotation
• **@SpringBootApplication**: Uygulamanın giriş noktrası olduğunu belirtir. Üç önemli özelliği etkinleştirir; auto-configuration, component scanning, configuration.    

- **@EnableAutoConfiguraion:** Spring Boot'un otomatik yapılandırma mekanizmasını açar. Projenin bağımlılıklarına göre gerekli beanleri otomatik olarak oluşturur.   
- **@ComponentScan:** Uygulamanın bulunduğu paketten başlayarak alt paketlerdeki @Component, @Service, @Repository, @Controller gibi classları tarar ve Spring context'e ekler.  
- **@SpringBootConfiguration:** Bu classın bir konfigürasyon classı olduğunu belirtir. Yani bu bean tanımları yapabileceğimiz anlamına gelir.    
