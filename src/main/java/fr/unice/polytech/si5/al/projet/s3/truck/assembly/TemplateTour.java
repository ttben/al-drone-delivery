package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import java.util.List;

public class TemplateTour {

	public List<TemplateDropPoint> dropPoints;

	public TemplateTour(List<TemplateDropPoint> dropPoints) {
		this.dropPoints = dropPoints;
	}

	public List<TemplateDropPoint> getDropPoints() {
		return dropPoints;
	}

	public void setDropPoints(List<TemplateDropPoint> dropPoints) {
		this.dropPoints = dropPoints;
	}
}
