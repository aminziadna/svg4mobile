package com.grub.svg4mobile;

import java.util.Enumeration;

import android.graphics.Canvas;

/**
 * Pinta figuras de tipo Rect
 * @see http://www.w3.org/TR/SVG11/shapes.html#RectElement
 */
public class BRect extends Figure {
	/**
	 * Crea un rectángulo con borde utilizando las clases Rect y Line 
	 */
	private Rect shape;
	private Boolean hasWidth = false;
	private Line borderTop;
	private Line borderDown;
	private Line borderLeft;
	private Line borderRight;
	private Transformations tr;
	
	/**
	 * Crea un rectángulo con borde
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @param w Ancho
	 * @param h Alto
	 * @param rx En rectángulos redondeados el radio del eje x de la elipse usada para redondear las esquinas.
	 * @param ry En rectángulos redondeados el radio del eje y  de la elipse usada para redondear las esquinas.
	 * @param rgb Código de color hexadecimal de la forma #FFFFFF
	 * @param brgb Código de color hexadecimal de la forma #FFFFFF para el borde
	 * @param bwidth Grosor del borde del rectángulo. Debe ser 0 para omitir el borde.
	 * @param tr Transformaciones que se aplicarán a la figura
	 */
	public BRect(float x, float y, float w, float h, float rx, float ry, String rgb, String brgb, float bwidth, Transformations tr) {
		this.tr = tr;
		this.shape = new Rect(x,y,(x+w),(y+h),rx,ry,rgb);
		this.hasWidth = ( bwidth > 0);
		if (this.hasWidth) {
			this.borderTop = new Line( x, y+h, x+w, y+h, brgb, bwidth );
			this.borderDown = new Line( x, y, x+w, y, brgb, bwidth );
			this.borderLeft = new Line( x, y+h, x, y, brgb, bwidth );
			this.borderRight = new Line( x+w, y, x+w, y+h, brgb, bwidth );
		}
	}
	
	/**
	 * Crea un rectángulo con borde
	 * @param x Coordenada x
	 * @param y Coordenada y
	 * @param w Ancho
	 * @param h Alto
	 * @param rgb Código de color hexadecimal de la forma #FFFFFF
	 * @param brgb Código de color hexadecimal de la forma #FFFFFF para el borde
	 * @param bwidth Grosor del borde del rectángulo. Debe ser 0 para omitir el borde.
	 * @param tr Transformaciones que se aplicarán a la figura
	 */
	public BRect(float x, float y, float w, float h, String rgb, String brgb, float bwidth, Transformations tr) {
		this(x, y, w, h, 0, 0, rgb, brgb, bwidth, tr);
	}
	
	/**
	 * Pinta la figura
	 * @param canvas
	 */
	public void draw(Canvas canvas) {
		//Se aplican las transformaciones a la figura
		canvas.save();
		this.tr.applyTransformations(canvas);
		this.shape.draw(canvas);
		if (this.hasWidth) {
			this.borderTop.draw(canvas);
			this.borderDown.draw(canvas);
			this.borderLeft.draw(canvas);
			this.borderRight.draw(canvas);
		}
		canvas.restore();
	}

	@Override
	public void addFigure(Figure f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enumeration<Figure> getFigures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFigure(Figure f) {
		// TODO Auto-generated method stub
		
	}
	
}
