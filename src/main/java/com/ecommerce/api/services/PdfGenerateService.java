package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.OrderDto;
import com.ecommerce.api.models.dtos.OrderItemsDto;
import com.ecommerce.api.models.dtos.UserDto;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.util.Collection;

public interface PdfGenerateService {
    void generatePdfFile(String templateName, OrderDto orderDto, Collection<OrderItemsDto> orderItemsDto, UserDto userDto, String pdfFileName);
}