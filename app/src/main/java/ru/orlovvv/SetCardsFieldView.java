package ru.orlovvv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

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
                p.setStyle(Paint.Style.FILL);

                canvas.drawOval(c.getX(), c.getY(), x2, y2, p);

                p.setColor(getColorBorder(c.getFill()));
                p.setStyle(Paint.Style.STROKE);
                canvas.drawOval(c.getX(), c.getY(), x2, y2, p);

                p.setStrokeWidth(0);
                p.setStyle(Paint.Style.FILL);

                String moneyValue = String.valueOf(c.getShape() * 100);
                String value = getValue(c.getCount());

                canvas.drawText(moneyValue + "" + value, x2 - c.getWidth() + 50, y2 - (c.getWidth() / 2) + 30, p);

            }
        }

    }


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
