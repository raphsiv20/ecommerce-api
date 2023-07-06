package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.OrderDto;
import com.ecommerce.api.models.dtos.OrderItemsDto;
import com.ecommerce.api.models.dtos.UserDto;
import com.ecommerce.api.services.PdfGenerateService;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {
    private Logger logger = LoggerFactory.getLogger(PdfGenerateServiceImpl.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${pdf.directory}")
    private String pdfDirectory;

    @Override
    public void generatePdfFile(String templateName, OrderDto orderDto, Collection<OrderItemsDto> orderItemsDto, UserDto userDto, String pdfFileName) {
        Context context = new Context();
        context.setVariable("orderInfos", orderDto);
        context.setVariable("orderItems", orderItemsDto);
        context.setVariable("user", userDto);

        String htmlContent = templateEngine.process(templateName, context);
        try {
            //StringBuilder sb = new StringBuilder();
            //System.out.println(sb.append("Chemin de téléchargement: ").append(pdfDirectory).append(" jespere que ca marche!").toString());
            //Resource resource = new ClassPathResource("static");
            //System.out.println("file: " + resource.getURI());
            //StringBuilder url = new StringBuilder();

            FileOutputStream fileOutputStream = new FileOutputStream(pdfDirectory + pdfFileName);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();

        } catch (FileNotFoundException | DocumentException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
