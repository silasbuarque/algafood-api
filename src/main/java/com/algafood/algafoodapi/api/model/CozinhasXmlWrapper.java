package com.algafood.algafoodapi.api.model;

import com.algafood.algafoodapi.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasXmlWrapper {

    @NonNull
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("cozinha")
    private List<Cozinha> cozinhas;

}
