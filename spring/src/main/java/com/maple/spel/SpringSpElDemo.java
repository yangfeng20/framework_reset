package com.maple.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author yangfeng
 * @date : 2023/5/29 15:40
 * desc:
 */

public class SpringSpElDemo {
    public static void main(String[] args) {
        ExpressionParser spElParser = new SpelExpressionParser();

        String text = "#[10 + 20]";
        Expression expression = spElParser.parseExpression(text);

        System.out.println(expression.getValue());

    }
}
