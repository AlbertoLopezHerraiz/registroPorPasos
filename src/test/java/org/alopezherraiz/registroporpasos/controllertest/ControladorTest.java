package org.alopezherraiz.registroporpasos.controllertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenUrl_whenGetRequest_thenFindGetResponse() throws Exception {
        String textoSolicitud = "/datos1";
        String textoEsperadoRespuesta = "/datos1";
        MockHttpServletRequestBuilder builder = post(textoSolicitud).param("usuario", "inexistente");
        this.mockMvc.perform(builder).andExpect(redirectedUrl(textoEsperadoRespuesta));
    }
    @Test
    public void givenInvalidParameters_whenGetRequestToDatos3_thenBadRequest() throws Exception {
        String invalidParameter = "invalid_param";
        MockHttpServletRequestBuilder builder = get("/datos3").param("parametro", invalidParameter);
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void givenMatchingPasswordsAndUser_whenPostRequestToDatos1_thenSuccess() throws Exception {
        String usuario = "miusuario";
        String clave = "123456";
        MockHttpServletRequestBuilder builder = post("/datos1")
                .param("usuario", usuario)
                .param("clave", clave)
                .param("validarClave", clave);

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void givenConditionNotMet_whenGetRequestToPaso1_thenNoRedirectToPaso2() throws Exception {
        boolean conditionMet = false;
        MockHttpServletRequestBuilder builder = get("/paso1")
                .param("condition", String.valueOf(conditionMet));

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("paso1"));
    }
}
