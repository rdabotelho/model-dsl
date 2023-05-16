package com.m2r.mdsl;

import com.m2r.easyparser.ParserException;
import com.m2r.mdsl.model.Domain;
import com.m2r.mdsl.model.DomainList;
import junit.framework.TestCase;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ModelDslTest extends TestCase {

    private void assertResult(DomainList result ) {
        assertNotNull(result);
        assertEquals(result.getDomains().size(), 2);
        Domain domain = result.getDomainByName("User");
        assertNotNull(domain);
        assertEquals(domain.getAttributes().size(), 4);
        assertNotNull(domain.getAttributeByName("name"));
        assertNotNull(domain.getAttributeByName("login"));
        assertNotNull(domain.getAttributeByName("password"));
        assertNotNull(domain.getAttributeByName("age"));
        domain = result.getDomainByName("State");
        assertNotNull(domain);
        assertEquals(domain.getAttributes().size(), 4);
        assertNotNull(domain.getAttributeByName("ACTIVE"));
        assertNotNull(domain.getAttributeByName("INACTIVE"));
        assertNotNull(domain.getAttributeByName("BLOCKED"));
        assertNotNull(domain.getAttributeByName("CANCELED"));
    }

    public void testParseFile() throws ParserException {
        ClassLoader classLoader = ModelDslTest.class.getClassLoader();
        URL resourceUrl = classLoader.getResource("test.model");
        String filePath = resourceUrl.getFile();
        File file = new File(filePath);
        DomainList result = ModelDsl.parse(file);
        assertResult(result);
    }

    public void testParseReader() throws ParserException {
        ClassLoader classLoader = ModelDslTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("test.model");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        DomainList result = ModelDsl.parse(reader);
        assertResult(result);
    }

    public void testParseString() throws ParserException {
        String code = "entity User {\n" +
                "   String name\n" +
                "   String login\n" +
                "   String password\n" +
                "   Integer age\n" +
                "}\n" +
                "enum State {\n" +
                "    ACTIVE\n" +
                "    INACTIVE\n" +
                "    BLOCKED\n" +
                "    CANCELED\n" +
                "}";
        DomainList result = ModelDsl.parse(code);
        assertResult(result);
    }

}