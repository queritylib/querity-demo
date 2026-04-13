package net.brunomendola.querity.demo.util;

import tools.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
  @SuppressWarnings("unchecked")
  public static <T> List<T> readListFromJson(String path, Class<T> entityClass) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Class<T[]> entityArrayClass = (Class<T[]>) Array.newInstance(entityClass, 0).getClass();
    return Arrays.asList(objectMapper.readValue(new ClassPathResource(path).getInputStream(), entityArrayClass));
  }
}
