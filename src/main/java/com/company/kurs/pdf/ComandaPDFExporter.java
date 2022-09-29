package com.company.kurs.pdf;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import com.company.kurs.query.ComandaAmount;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ComandaPDFExporter {
    private List<ComandaAmount> comandaAmounts;

    public ComandaPDFExporter(List<ComandaAmount> comandaAmounts) {
        this.comandaAmounts = comandaAmounts;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Team", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Players", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(ComandaAmount comandaAmount : comandaAmounts){
            table.addCell(comandaAmount.getName_com());
            table.addCell(comandaAmount.getAmount().toString());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        Paragraph title = new Paragraph("Player-List for every team", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {3.5f, 1.5f});


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
