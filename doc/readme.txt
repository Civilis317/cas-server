Setup:
Create folder cas-server
Create separate folder temp

In temp folder open console
git clone https://github.com/apereo/cas-overlay-template.git
cd cas-overlay-template
git checkout 5.3

copy pom.xml and License.txt from temp to folder cas-server



create keystore for ssl setup
- localhost:
keytool -genkey -keyalg RSA -alias ssl-cert -keystore cas-keystore -storepass welcome1 -validity 360 -keysize 2048
What is your first and last name?
  [Unknown]:  localhost
What is the name of your organizational unit?
  [Unknown]:  localhost
What is the name of your organization?
  [Unknown]:  localhost
What is the name of your City or Locality?
  [Unknown]:  BOIP
What is the name of your State or Province?
  [Unknown]:  Benelux
What is the two-letter country code for this unit?
  [Unknown]:  BX
Is CN=localhost, OU=localhost, O=localhost, L=BOIP, ST=Benelux, C=BX correct?
  [no]:  yes
Enter key password for <ssl-cert>
        (RETURN if same as keystore password):
Re-enter new password:


export certificate
keytool -export -alias ssl-cert -file cas-ssl-certificate.crt -keystore cas-keystore

create truststore for client, containing certificate
keytool -import -alias cas-ssl-cert -storepass welcome1 -file cas-ssl-certificate.crt -keystore trustStore.jks
