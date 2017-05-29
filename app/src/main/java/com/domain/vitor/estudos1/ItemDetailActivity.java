package com.domain.vitor.estudos1;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Bundle b = this.getIntent().getExtras();
        if(b!=null){
            Item myItem = (Item)b.getSerializable("ITEM_KEY");

            TextView nameView = (TextView)findViewById(R.id.item_detail_name);
            TextView attributesView = (TextView)findViewById(R.id.item_detail_details);
            TextView descriptionView = (TextView)findViewById(R.id.item_detail_description);

            nameView.setText(myItem.item_name);
            attributesView.setText(myItem.details);
            descriptionView.setText(myItem.description);

            if(myItem.table != null){
                LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

                TableLayout tableLayout  = new TableLayout(this);
                tableLayout.setLayoutParams(tableParams);

                for(int i = 0; i<myItem.table.rows;i++)
                {
                    TableRow newRow = new TableRow(this);
                    for(int j = 0; j<myItem.table.columns;j++)
                    {
                        TextView colTextView = new TextView(this);
                        colTextView.setText(myItem.table.stringArray[i][j]);
                        newRow.addView(colTextView);
                    }
                    tableLayout.addView(newRow);
                }

                LinearLayout mainLayout = (LinearLayout)findViewById(R.id.item_detail_scroll_layout);
                mainLayout.addView(tableLayout);

            }


        }



    }

}
