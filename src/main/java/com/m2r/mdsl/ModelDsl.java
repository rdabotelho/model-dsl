package com.m2r.mdsl;

import com.m2r.easyparser.ParserException;
import com.m2r.mdsl.model.DomainList;
import com.m2r.mdsl.parser.ModelParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ModelDsl {

    public static DomainList parse(File file) throws ParserException {
        try (Reader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            return ModelParser.parse(new BufferedReader(reader));
        } catch (IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }

    public static DomainList parse(Reader reader) throws ParserException {
        return ModelParser.parse(reader);
    }

    public static DomainList parse(String code) throws ParserException {
        try (Reader reader = new BufferedReader(new StringReader(code))) {
            return ModelParser.parse(reader);
        } catch (IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }

}
