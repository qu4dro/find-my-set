package ru.orlovvv;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.orlovvv.Card;
import ru.orlovvv.CardField;
import ru.orlovvv.R;

public class SetCardsFieldView extends View {

    private CardField cardField;
    private int width = 0, height = 0;
    private Paint p = new Paint();

    public void setCardField(CardField cardField) {
        this.cardField = cardField;
        cardField.setDrawCoordinates(width, height);
        invalidate();
    }


    public SetCardsFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public ArrayList<Card> checkSet() {

        if (cardField.isSelectedAreSet(cardField.getSelectedCards())) {
            Log.d("mytag", "checkSet: " + "SET");
            return cardField.getSelectedCards();

        } else {
            Toast toast = Toast.makeText(getContext(),
                    "Это не сет", Toast.LENGTH_LONG);
            toast.show();
            invalidate();
        }

        return null;
    }

    public CardField getCardField() {
        return cardField;
    }
    //    public void findSet() {
//        if (!cardField.findSet()) {
//            Toast toast = Toast.makeText(getContext(),
//                    "There's no set", Toast.LENGTH_LONG);
//            toast.show();
//        }
//
//        invalidate();
//
//    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.width = getWidth();
        this.height = getHeight();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        if (cardField != null) {
            ArrayList<Card> cards = cardField.getCards();

            int cardBackground;
            int selectedCardBackground = Color.rgb(191, 191, 191);
            Integer border;


            for (Card c : cards) {
                int x2 = c.getX() + c.getWidth();
                int y2 = c.getY() + c.getHeight();
                cardBackground = getColor(c.getColor());
                if (cardField.getSelectedCards().contains(c)) {
                    p.setColor(cardBackground);
                    p.setStrokeWidth(20);
                    canvas.drawRoundRect(c.getX(), c.getY(), x2, y2, 25, 25, p);
                } else {
                    p.setStrokeWidth(10);

                    p.setColor(cardBackground);
                }

                p.setTextSize(100);
//                //card
                p.setStyle(Paint.Style.FILL);

                canvas.drawOval(c.getX(), c.getY(), x2, y2, p);

//
//                //border
                p.setColor(getColorBorder(c.getFill()));
                p.setStyle(Paint.Style.STROKE);
                canvas.drawOval(c.getX(), c.getY(), x2, y2, p);
//
                //shapes inside card
                p.setStrokeWidth(0);
                p.setStyle(Paint.Style.FILL);
//                p.setColor();
                String moneyValue = String.valueOf(c.getShape() * 100);
                String value = getValue(c.getCount());
//                p.setColor(color);

                canvas.drawText(moneyValue + "" + value, x2 - c.getWidth() + 50, y2 - (c.getWidth() / 2) + 30, p);


//                int fig_height = (c.getHeight() - 80) / 3;
//                int fig_x = c.getX() + 10;
//                int fig_y = calculateFigY(c.getCount(), c.getHeight(), c.getY() , fig_height);
//
//                int fig_x2 = fig_x + (c.getWidth()-20);
//                int fig_y2 = fig_y + fig_height;
//
//                p.setStrokeWidth(3);
//                for (int i = 0; i < c.getCount(); i++) {
//
//                    drawFillFigure(canvas, fig_x, fig_y, fig_x2 , fig_y2, c.getFill(), c.getShape());
//
//                    fig_y += fig_height + 20;
//                    fig_y2 = fig_y + fig_height;
//
//                }
            }
        }

    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void drawFillFigure(Canvas c, int x1, int y1, int x2, int y2, int fill, int shape) {
//        switch (fill) {
//            case 1: drawDashedLineFigure(c, x1, y1, x2 , y2, shape);
//                break;
//            case 2: {
//                p.setStyle(Paint.Style.STROKE);
//                drawShape(c, x1, y1, x2 , y2, shape);
//            }
//            break;
//            case 3: {
//                p.setStyle(Paint.Style.FILL);
//                drawShape(c, x1, y1, x2 , y2, shape);
//            }
//            break;
//        }
//    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void drawDashedLineFigure(Canvas c, int x1, int y1, int x2, int y2, int shape) {
//        p.setStyle(Paint.Style.STROKE);
//        double k = 1.2;
//        int width = x2 - x1;
//        int height = y2 - y1;
//
//        int iterations = height / 6;
//
//        int w2, h2, x_koef, y_koef;
//
//        for (int i = 0; i < iterations; i++) {
//            if (i == 0) {
//                p.setStrokeWidth(3);
//            } else {
//                p.setStrokeWidth(1);
//            }
//            drawShape(c, x1, y1, x2 , y2, shape);
//
//            w2 = (int) (width / k);
//            h2 = (int) (height / k);
//
//            x_koef = (width - w2)/2;
//            y_koef = (height - h2)/2;
//            x1 += x_koef;
//            x2 -= x_koef;
//            y1 += y_koef;
//            y2 -= y_koef;
//
//            height = h2;
//            width = w2;
//
//        }
//    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void drawShape(Canvas c, int x1, int y1, int x2, int y2, int shape) {
//        switch (shape) {
//            case 1: drawRhombus(c, x1, y1, x2, y2);
//                break;
//            case 2: c.drawRoundRect(new RectF(x1, y1, x2 , y2), 5, 5, p);
//                break;
//            case 3: c.drawOval( x1, y1, x2, y2, p);
//                break;
//        }
//
//    }


//    private void drawRhombus(Canvas c, int x1, int y1, int x2, int y2) {
//        Path path = new Path();
//        int center_width = (x2 + x1) / 2;
//        int center_height = (y2 + y1) / 2;
//
//        path.moveTo(center_width, y1); // Top
//        path.lineTo(x1, center_height); // Left
//        path.lineTo(center_width, y2); // Bottom
//        path.lineTo(x2, center_height); // Right
//        path.lineTo(center_width, y1); // Back to Top
//        path.close();
//
//        c.drawPath(path, p);
//    }


//    private int calculateFigY(int count, int card_height, int card_y, int fig_height) {
//        int fig_y;
//        int center = (card_y + (card_y + card_height)) / 2;
//        switch (count) {
//            case 1: fig_y = center - (fig_height / 2);
//                break;
//            case 2: fig_y = center - (fig_height + 10);
//                break;
//            default: fig_y = card_y + 20;
//                break;
//        }
//        return  fig_y;
//    }


    private int getColor(int cardColor) {
        int bronze = getResources().getColor(R.color.bronze);
        int silver = getResources().getColor(R.color.silver);
        int gold = getResources().getColor(R.color.gold);

        if (cardColor == 1) return bronze;

        if (cardColor == 2) return silver;

        return gold;
    }

    private int getColorBorder(int cardColor) {
        int black = getResources().getColor(R.color.black);
        int red = getResources().getColor(R.color.red);
        int green = getResources().getColor(R.color.green);

        if (cardColor == 1) return black;

        if (cardColor == 2) return red;

        return green;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Log.d("mytag", "onTouchEvent: " + cardField.getSelectedCards());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (cardField.cardTouched(x, y)) {
                invalidate();
            }
        }

        return super.onTouchEvent(event);
    }

    public String getValue(int value) {
        if (value == 1) return "$";
        if (value == 2) return "€";
        else return "\u20BD";
    }


}
