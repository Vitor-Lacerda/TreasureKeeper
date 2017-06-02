package com.domain.vitor.estudos1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


//Essa activity mostra uma lista dos itens magicos do DMG

//Essa activity e a primeira Activity que abre no momento.

public class ItemListActivity extends ListActivity {

    private ItemAdapter myAdapter;

    private final Item.Category[] categoryValues = Item.Category.values();
    private final Item.Rarity[] rarityValues = Item.Rarity.values();

    private ArrayList<Item> displayedItems;
    private ArrayList<Item> jsonItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        setButtons();
       // ArrayList<Item> items = makeItemList();
        jsonItems = makeItemListFromJSON();

        displayedItems = jsonItems;

        myAdapter = new ItemAdapter(this, displayedItems);
        setListAdapter(myAdapter);
        sortItemsByName();



    }

    private void setButtons(){
        Button byName = (Button)findViewById(R.id.sort_by_name_button);
        byName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortItemsByName();
            }
        });

        Button byCategory = (Button)findViewById(R.id.sort_by_category_button);
        byCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortItemsByCategory();
            }
        });

        Button byRariry = (Button)findViewById(R.id.sort_by_rarity_button);
        byRariry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortItemsByRarity();
            }
        });

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent();
        Bundle b = new Bundle();

        //Item clickedItem = (Item) getListAdapter().getItem(position);

        //b.putSerializable("ITEM_KEY", clickedItem);
        //i.setClass(this, ItemDetailActivity.class);
        b.putParcelableArrayList(getResources().getString(R.string.intent_item_list_key), displayedItems);
        b.putInt(getResources().getString(R.string.intent_item_index_key), position);
        i.setClass(this, ItemDetailPagerActivity.class);

        i.putExtras(b);
        startActivity(i);


    }





    private void sortItemsByName(){
        Collections.sort(displayedItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.item_name.compareTo(o2.item_name);
            }
        });
        myAdapter.setDataSource(displayedItems);
        setListAdapter(myAdapter);
    }

    private void sortItemsByCategory(){
        Collections.sort(displayedItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if(o1.category == o2.category){
                    if(o1.rarity == o2.rarity){
                        return o1.item_name.compareTo(o2.item_name);
                    }
                    return o1.rarity.compareTo(o2.rarity);
                }
                return o1.category.compareTo(o2.category);

            }
        });
        myAdapter.setDataSource(displayedItems);
        setListAdapter(myAdapter);
    }

    private void sortItemsByRarity(){
        Collections.sort(displayedItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if(o1.rarity == o2.rarity){
                    if(o1.category == o2.category){
                        return o1.item_name.compareTo(o2.item_name);
                    }
                    return o1.category.compareTo(o2.category);
                }
                return o1.rarity.compareTo(o2.rarity);
            }
        });
        myAdapter.setDataSource(displayedItems);
        setListAdapter(myAdapter);

    }


    /*
    ArrayList<Item> makeItemList(){

        ArrayList<Item> items = new ArrayList<Item>();
        //NAME, CATEGORY, SUBTYPE, RARITY, ATTUNEMENT, ATTUNEMENT DESCRIPTION, DESCRIPTION
        items.add(new Item("Adamantine Armor", Item.Category.ARMOR, "medium or heavy, not hide", Item.Rarity.UNCOMMON, false, "", "This suit of armor is reinforced with adamantine, one " +
                "of the hardest substances in existence. While you're " +
                "wearing it, any critical hit against you becomes a " +
                "normal hit."));


        String[][] alchemyJugTable = new String[11][2];

        alchemyJugTable[0][0] = "Liquid"; alchemyJugTable[0][1] = "Max Amount";
        alchemyJugTable[1][0] = "Acid"; alchemyJugTable[1][1] = "8 ounces";
        alchemyJugTable[2][0] = "Basic poison"; alchemyJugTable[2][1] = "1/2 ounce";
        alchemyJugTable[3][0] = "Beer "; alchemyJugTable[3][1] = "4 gallons";
        alchemyJugTable[4][0] = "Honey"; alchemyJugTable[4][1] = "1 gallon";
        alchemyJugTable[5][0] = "Mayonnaise"; alchemyJugTable[5][1] = "2 gallons";
        alchemyJugTable[6][0] = "Oil"; alchemyJugTable[6][1] = "1 quart";
        alchemyJugTable[7][0] = "Vinegar"; alchemyJugTable[7][1] = "2 gallons";
        alchemyJugTable[8][0] = "Water,fresh"; alchemyJugTable[8][1] = "8 gallons";
        alchemyJugTable[9][0] = "Water, salt"; alchemyJugTable[9][1] = "12 gallons";
        alchemyJugTable[10][0] = "Wine"; alchemyJugTable[10][1] = "1 gallon";




        items.add(new Item("Alchemy Jug", Item.Category.WONDROUS, "", Item.Rarity.UNCOMMON, false, "", "This ceramic jug appears to be able to hold a gallon " +
                "of liquid and weighs 12 pounds whether full or empty. " +
                "Sloshing sounds can be heard from within the jug when " +
                "it is shaken, even if the jug is empty. " +
                "You can use an action and name one liquid from the " +
                "table below to cause the jug to produce the chosen " +
                "liquid. Afterward, you can uncork the jug as an action " +
                "and pour that liquid out, up to 2 gallons per minute. The " +
                "maximum amount of liquid the jug can produce depends " +
                "on the liquid you named. " +
                "Once the jug starts producing a liquid, it can't produce " +
                "a different one, or more of one that has reached its " +
                "maximum, until the next dawn.",11,2,alchemyJugTable));





        items.add(new Item("Ammunition +1", Item.Category.WEAPON, "any ammunition", Item.Rarity.UNCOMMON, false, "", "You have a +1 bonus to attack and damage rolls made " +
                "with this piece of magic ammunition. Once it hits " +
                "a target, the ammunition is no longer magical."));
        items.add(new Item("Ammunition +2", Item.Category.WEAPON, "any ammunition", Item.Rarity.RARE, false, "", "You have a +2 bonus to attack and damage rolls made " +
                "with this piece of magic ammunition. Once it hits " +
                "a target, the ammunition is no longer magical."));
        items.add(new Item("Ammunition +3", Item.Category.WEAPON, "any ammunition", Item.Rarity.VERY_RARE, false, "", "You have a +3 bonus to attack and damage rolls made " +
                "with this piece of magic ammunition. Once it hits " +
                "a target, the ammunition is no longer magical."));
        items.add(new Item("Amulet of Health", Item.Category.WONDROUS, "", Item.Rarity.RARE, true, "", "Your Constitution score is 19 while you wear this " +
                "amulet. It has no effect on you if your Constitution is " +
                "already 19 or higher."));
        items.add(new Item("Amulet of Proof Against Detection and Location", Item.Category.WONDROUS, "", Item.Rarity.UNCOMMON, true, "", "While wearing this amulet, you are hidden from " +
                "divination magic. You can't be targeted by such magic or " +
                "perceived through magical scrying sensors."));
        items.add(new Item("Amulet of the Planes", Item.Category.WONDROUS, "", Item.Rarity.VERY_RARE, true, "", "While wearing this amulet, you can use an action to " +
                "name a location that you are familiar with on another " +
                "plane of existence. Then make a DC 15 Intelligence " +
                "check. On a successful check, you cast the plane shift " +
                "spell. On a failure, you and each creature and object " +
                "within 15 feet of you travel to a random destination. " +
                "Roll a dlOO. On a 1- 60, you travel to a random location " +
                "on the plane you named. On a 61- 100, you travel to a " +
                "randomly determined plane of existence."));

        return items;

    }
    */

    String loadJSONfromAsset()
    {
        String json = null;
        try{
            InputStream is = getAssets().open("items.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    ArrayList<Item> makeItemListFromJSON()
    {
        ArrayList<Item> items = new ArrayList<Item>();

        try {
            String json = loadJSONfromAsset();
            JSONObject obj = new JSONObject(json);
            JSONArray magicItems = obj.getJSONArray("magicItems");

            for(int i = 0; i<magicItems.length(); i++){
                JSONObject nextItem = magicItems.getJSONObject(i);
                String name = nextItem.getString("name");
                int page = nextItem.getInt("page");
                Item.Category category = categoryValues[nextItem.getInt("category")];
                String type = nextItem.getString("type");
                Item.Rarity rarity = rarityValues[nextItem.getInt("rarity")];
                boolean req_attunement = nextItem.getBoolean("req_attunement");
                String attunement = nextItem.getString("attunement");
                String description = nextItem.getString("description");
                if(nextItem.getBoolean("table")){
                    int r = nextItem.getInt("rows");
                    int c = nextItem.getInt("cols");

                    String[][] table = new String[r][c];


                    JSONArray content = nextItem.getJSONArray("table_contents");
                    for(int k = 0; k<content.length();k++) {
                        for (int col = 0; col < c; col++) {
                            JSONObject cObj = content.getJSONObject(k);
                            String colContent = cObj.getString("col" + col);
                            table[k][col] = colContent;
                        }
                    }

                    Item newItem = new Item(name, page, category, type, rarity, req_attunement, attunement, description, r, c, table);
                    items.add(newItem);
                }
                else{
                    Item newItem = new Item(name, page, category, type, rarity, req_attunement, attunement, description);
                    items.add(newItem);
                }


            }



        } catch (JSONException e) {
            e.printStackTrace();
            return items;
        }

        return items;


    }

}
