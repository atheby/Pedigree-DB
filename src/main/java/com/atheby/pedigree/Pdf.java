package com.atheby.pedigree;

import java.io.*;
import java.util.*;
import java.util.List;

import com.atheby.pedigree.horse.Horse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Pdf {
	
	private static Document pdf;
	private static PdfContentByte cb;
	
	public static void generatePdf(Map<Integer, List<Horse>> pedigree) {
		
		Rectangle page = new Rectangle(200 * pedigree.size(), 250 * pedigree.size() * 2);
		page.setBackgroundColor(new BaseColor(230,230,210));
		pdf = new Document(page, 30, 0, 30, 0);

		try {
			PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(pedigree.get(0).get(0).getName() + "_" + (pedigree.size() - 1) + "gen"));
			pdf.open();
			Paragraph pr = new Paragraph();
			cb = writer.getDirectContent();
			
			pr.setFont(new Font(Font.FontFamily.HELVETICA  , 30, Font.BOLDITALIC));
			if(pedigree.size() - 1 == 1)
				pr.add((pedigree.size() - 1) + " generation of " + pedigree.get(0).get(0).getName());
			else
				pr.add((pedigree.size() - 1) + " generations of " + pedigree.get(0).get(0).getName());
			pr.setAlignment(Element.ALIGN_LEFT);
			pdf.add(pr);
			
			try {
				cb.setFontAndSize(BaseFont.createFont(), 10);
			}catch(IOException e){
			}
			
			List<Horse> horses;
			
			for(int i = 0; i < pedigree.size(); i++) {
				
				horses = pedigree.get(i);				
				float heightSpacing = (250 * pedigree.size() * 2) / (horses.size() + 1);
				float height = heightSpacing;
				float leafHeightSpacing = (250 * pedigree.size() * 2) / (horses.size() * 2 + 1);
				float leafHeight = leafHeightSpacing;
				
				for(Horse hr : horses) {
					if(hr != null) {
						addHorseBlock(hr , 100 + (200 * i), height);
						if(i < pedigree.size() - 1) {
							if(hr.getDam() != null) {
								cb.saveState();
								cb.setLineWidth(1);
								cb.setColorStroke(new BaseColor(0, 0, 0));
								cb.moveTo((100 + (200 * i) + 50), height + 2);
								cb.lineTo((100 + (200 * i) + 50), leafHeight + leafHeightSpacing);
								cb.lineTo((100 + (200 * (i + 1)) - 50), leafHeight + leafHeightSpacing);
								cb.stroke();
								cb.restoreState();
							}
							if(hr.getSire() != null) {
								cb.saveState();
								cb.setLineWidth(1);
								cb.setColorStroke(new BaseColor(0, 0, 0));
								cb.moveTo((100 + (200 * i) + 50), height + 2);
								cb.lineTo((100 + (200 * i) + 50), leafHeight);
								cb.lineTo((100 + (200 * (i + 1)) - 50), leafHeight);
								cb.stroke();
								cb.restoreState();
							}
						}
					}
					height += heightSpacing;
					leafHeight += 2 * leafHeightSpacing; 
				}
			}
		} catch (FileNotFoundException e) {
		} catch (DocumentException e) {
		}
		pdf.close();
	}
	private static void addHorseBlock(Horse hr, float x, float y) {
		cb.saveState();
		cb.setColorStroke(new BaseColor(230,210,180));
		cb.rectangle(x - 50 ,y - 20, 100, 40);
		cb.setColorFill(new BaseColor(230,210,180));
		cb.fill();
		cb.stroke();
		cb.restoreState();
		cb.beginText();
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, hr.getName(), x - 45, y + 8, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_CENTER, hr.getDob().getDate(), x, y - 4, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, hr.getBreeder().getName(), x - 45, y - 16, 0);
		cb.endText();
	}
}
