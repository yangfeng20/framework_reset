package com.maple.myspring.util;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 杨锋
 * @date 2022/10/29 14:14
 * desc:
 */

@Slf4j
public class XmlUtil {

    //private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public static List<Element> parse(InputStream inputStream) throws Exception{
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);

        // 获取所有bean标签
        List<Node> beanList = document.selectNodes("//bean");
        return beanList.stream().map(node -> (Element) node).collect(Collectors.toList());
    }
}
