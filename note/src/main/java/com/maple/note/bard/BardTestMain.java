package com.maple.note.bard;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author yangfeng
 * @date : 2023/10/23 11:08
 * desc:
 */

public class BardTestMain {
    public static void main(String[] args) {
        System.out.println(StringEscapeUtils.unescapeJava("\\u4f60\\u597d\\uff0c\\u4f60\\u662fgpt4\\u5417"));

        //AIClient client = new GoogleBardClient("bwg4J0pF3DYwOqF3J3-whdBYU9qSDBQqUpRdxnUvuZnkGOIGSkY80TRBwxDzk1FwAjhwBQ.;sidts-CjIB3e41hdQrhm6nmZs8IuBJprjB8Y5m6LZQGMmLTms8X2BMmCwjq_VRGznAWqApJKcdGxAA");
        //NetworkUtils.setUpProxy("localhost", "7890");
        //Answer answer = client.ask("你好");
        //System.out.println(answer);
    }
}
