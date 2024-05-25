package com.whitelopes.liter.integrations;

import com.whitelopes.liter.dto.GutendexBooksResponseDto;
import com.whitelopes.liter.helper.ConvertData;

public class GutendexIntegration {
    public static String baseUrl = "https://gutendex.com/";

    public static GutendexBooksResponseDto ConsultBook(String bookName){
        var response = CallApi.Call( baseUrl + "books/?search=" + bookName.trim().replace(" ", "%20"));

        return ConvertData.convert(response, GutendexBooksResponseDto.class);
    }

//
//        var convertedData = ConvertData.convert(response, GutendexBooksResponseDto.class);
}
