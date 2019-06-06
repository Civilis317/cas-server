ARG SOURCE_IMAGE
ARG SRC

FROM $SOURCE_IMAGE
MAINTAINER lzeilstra@vxcompany.com

COPY cas.war cas.war

EXPOSE 6443

ENTRYPOINT ["java", "-jar", "cas.war"]
