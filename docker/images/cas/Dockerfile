ARG SOURCE_IMAGE

FROM $SOURCE_IMAGE
MAINTAINER ext-lzeilstra@boip.int

COPY thekeystore /etc/cas/thekeystore
COPY cas.properties /etc/cas/config/cas.properties
#COPY passwd.txt /etc/cas/config/passwd.txt
#COPY user-details.properties /etc/cas/config/user-details.properties
COPY log4j2.xml /etc/cas/config/log4j2.xml
