module logto.java {
    exports nz.geek.goodwin.logto.domain.dto;
    exports nz.geek.goodwin.logto.domain.exceptions;
    exports nz.geek.goodwin.logto.external;

    requires unirest.java;
    requires unirest.objectmapper.jackson;
    requires com.fasterxml.jackson.annotation;
}