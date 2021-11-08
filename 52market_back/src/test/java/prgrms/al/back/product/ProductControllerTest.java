package prgrms.al.back.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import prgrms.al.back.location.service.LocationService;
import prgrms.al.back.product.controller.ProductController;
import prgrms.al.back.product.dto.ProductCreateRequest;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.dto.SellerDto;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    LocationService locationService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .apply(documentationConfiguration(restDocumentation))
            .build();
    }

    private String toJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Test
    @DisplayName("상품을 성공적으로 등록할 경우 HTTP 상태코드 201을 반환한다.")
    void createProduct() throws Exception {

        when(productService.createProduct(any())).thenReturn(1L);

        SellerDto seller = new SellerDto("tester", "tester_nickname", "이문동", 36.5);

        ProductCreateRequest request = ProductCreateRequest.builder()
            .title("test title")
            .content("test content")
            .price(100_000L)
            .location(seller.getLocation())
            .seller(seller)
            .imageUrls(Arrays.asList("sample.image.url1", "sample.image.url2"))
            .build();

        mockMvc.perform(post("/api/products")
                .characterEncoding(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(request)))
            .andExpect(status().isCreated())
            .andDo(
                document("/product-post",
                    preprocessRequest(prettyPrint()),
                    requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("상품 게시글의 제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("상품 게시글의 설명"),
                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품의 가격"),
                        fieldWithPath("seller.name").type(JsonFieldType.STRING).description("판매자의 이름"),
                        fieldWithPath("seller.nickname").type(JsonFieldType.STRING).description("판매자의 닉네임"),
                        fieldWithPath("seller.location").type(JsonFieldType.STRING).description("판매자의 위치"),
                        fieldWithPath("seller.mannerTemperature").type(JsonFieldType.NUMBER).description("판매자의 매너온도"),
                        fieldWithPath("imageUrls").type(JsonFieldType.ARRAY).description("상품 이미지의 주소 리스트"),
                        fieldWithPath("location").type(JsonFieldType.STRING).description("상품이 판매되는 위치")
                    ),
                    responseHeaders(
                        headerWithName("Location").description("생성된 게시글의 ID가 담긴 URI")
                    )));
    }
}
