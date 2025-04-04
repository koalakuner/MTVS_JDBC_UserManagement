package com.ohgiraffers.jdbc.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* QueryUtil 클래스 목적
 SQL 쿼리들을 모아두는 곳
 별도의 XML 파일에 모아두고 관리함으로써 유지보수 용이
 필요에 따라 여러 곳에서 재사용 가능
 SQL 쿼리를 수정해야 할 경우, JAVA 코드 수정하지 않고 XML 파일만 수정하면 됨.
 */
public class QueryUtil {
    private  static final Map<String, String> queries = new HashMap<>();

    static {
        loadQueries();
    }

    private static void loadQueries() {
        try (InputStream is = QueryUtil.class.getClassLoader().getResourceAsStream("queries.xml")) {

            if (is == null) {
                throw new RuntimeException("queries.xml 파일을 찾을 수 없습니다.");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(is);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("query");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element queryElement = (Element) nodeList.item(i);
                String id = queryElement.getAttribute("id");
                String sql = queryElement.getTextContent().trim();
                queries.put(id, sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("쿼리 로딩 중 오류 발생", e);
        }
    }
// 특정 쿼리 ID로 SQL 가져오기

    public static String getQuery (String id){
        return queries.get(id);
    }
}