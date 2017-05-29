package com.domain.vitor.estudos1;



import java.io.Serializable;



public class Item implements Serializable {


    public enum Category {
        ARMOR("Armor"),//0
        POTION("Consumable(Potion)"),//1
        RING("Ring"),//2
        ROD("Rod"),//3
        SCROLL("Consumable(Scroll)"),//4
        STAFF("Staff"),//5
        WAND("Wand"),//6
        WEAPON("Weapon"),//7
        WONDROUS("Wondrous item");//8

        private final String description;

        private Category(String s) {
            description = s;
        }

        @Override
        public String toString(){
            return description;
        }
    }

    public enum Rarity {
        COMMON("Common"),//0
        UNCOMMON("Uncommon"),//1
        RARE("Rare"),//2
        VERY_RARE("Very rare"),//3
        LEGENDARY("Legendary"),//4
        VARIES("rarity varies");//5

        private final String description;

        private Rarity(String s) {
            description = s;
        }

        @Override
        public String toString(){
            return description;
        }
    }



    public boolean requires_attunement;
    public String item_name, item_type, attunement_description, description;
    public Rarity rarity;
    public Category category;
    public String details;
    public Table table;

    public Item(String _name, Category _cat, String _type, Rarity _rarity, boolean req_attunement, String _attunement, String _description) {
        item_name = _name;
        category = _cat;
        item_type = _type;
        rarity = _rarity;
        requires_attunement = req_attunement;
        attunement_description = _attunement;
        description = _description;

        String short_description = category.toString();
        if(category == Item.Category.ARMOR || category == Item.Category.WEAPON){
            short_description = short_description.concat("(" + item_type + ")");
        }
        short_description = short_description.concat("," + rarity.toString());
        if(requires_attunement){
            short_description = short_description.concat("(requires attunement "+ attunement_description +")" );
        }

        details = short_description;
        table = null;

    }

    public Item(String _name, Category _cat, String _subType, Rarity _rarity, boolean req_attunement, String _attunement,
                String _description, int rows, int columns, String[][] tableContent) {

        this(_name, _cat, _subType, _rarity, req_attunement, _attunement, _description);


        table = new Table(rows, columns, tableContent);


    }



}
