package com.example.a_g_maze;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.BulletSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MazeView extends View{

	private static final int W = 48; 		// ширина стенок
	private static final int RADIUS = 23; //радиус круга
	private static final int RADIUS2 = 5; //радиус круга, где был робот
	private static final int C = 24; 		// для центра круга
	private static final int W_MAX = (W+1)*12;
	private static final int W_MIN = W;
	private boolean WIN;

	private char[][] lab;

	Maze m1 = new Maze(1, getContext());

	
	public MazeView(Context context) {
		super(context);
	}

	public void onDraw(Canvas canvas){
		
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
		
		lab = m1.getMaze();

		for(int row = 0; row < m1.HEIGHT; row++) {
            for(int col = 0; col < m1.WIDTH; col++) {
                if (lab[col][row] == '1') {
                	paint.setColor(Color.GRAY);
                	canvas.drawRect((col+1)*W, (row+1)*W, (col+2)*W, (row+2)*W, paint);
            	} else if (lab[col][row] == '3') {
            		paint.setColor(Color.YELLOW);
                	canvas.drawRect((col+1)*W, (row+1)*W, (col+2)*W, (row+2)*W, paint);
                } else if (lab[col][row] == '4') {
            		paint.setColor(Color.RED);
            		canvas.drawCircle((col+1)*W+C, (row+1)*W+C, RADIUS2, paint);
                }
            }
		}

		paint.setColor(Color.RED);
		canvas.drawCircle(m1.touchX, m1.touchY, RADIUS, paint);

		if (WIN) {
			paint.setColor(Color.MAGENTA);
			paint.setTextSize(75);
			String text = "Урра!!!";
			paint.setStyle(Paint.Style.FILL);
			canvas.drawText(text, 200, 200, paint);
		}
		
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		
		int x, y, xWall, yWall, lX, lY; 
		float lastX, lastY;
		
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			
			lastX = m1.touchX;
			lastY = m1.touchY;
			lX = Math.round((m1.touchX+18)/W);
			lY = Math.round((m1.touchY+18)/W);
			
			x = Math.round((event.getX()+18)/W) * W - C;
			y = Math.round((event.getY()+18)/W) * W - C;
			
			xWall = Math.round((event.getX()+18)/W);
			yWall = Math.round((event.getY()+18)/W);
			
			double xCheck = lastX - x;
			double yCheck = lastY - y;
			
							
			if (x < W_MAX && y < W_MAX && x > W_MIN && y > W_MIN) {
				
				if (xCheck < -W || xCheck > W || yCheck < -W || yCheck > W) {
				} else if (lab[xWall-2][yWall-2] == '3') {
					
					m1.touchX = x; 
					m1.touchY = y;
		
					WIN = true;
					
				} else if (lab[xWall-2][yWall-2] != '1' && WIN != true) {
					
					m1.touchX = x; 
					m1.touchY = y;
					
					m1.setCell(lY-2, lX-2, '4');
				
				}
						
			} 
			
			invalidate();

		}
		
		return true;
		
	}
	
}

