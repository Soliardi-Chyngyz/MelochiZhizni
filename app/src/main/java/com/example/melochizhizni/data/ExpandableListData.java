package com.example.melochizhizni.data;

import com.example.melochizhizni.data.models.ExpandableCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData {
    public static ArrayList<ExpandableCategory> getData() {

        ExpandableCategory kitchen = new ExpandableCategory("Кухня");
        ArrayList<String> object = new ArrayList<>();
        object.add("Чашки");
        object.add("Посуда");
        object.add("Хлебница");
        object.add("Сушилка для посуды");
        object.add("Вазы");
        object.add("Столовые приборы");
        object.add("Кухонные аксессуары");
        kitchen.setItems(object);

        ExpandableCategory bath = new ExpandableCategory("Ванная");
        ArrayList<String> bathroom = new ArrayList<>();
        bathroom.add("Шторка для ванной");
        bathroom.add("Карниз для штор");
        bathroom.add("Набор для ванной");
        bath.setItems(bathroom);

        ExpandableCategory baby = new ExpandableCategory("Для детей");
        ArrayList<String> child = new ArrayList<>();
        child.add("Ванночка для купания");
        child.add("Детская посуда");
        child.add("Горшок");
        child.add("Игрушки");
        baby.setItems(child);

        ExpandableCategory techno = new ExpandableCategory("Бытовая техника");
        ArrayList<String> electric = new ArrayList<>();
        electric.add("Вся техника");
        electric.add("Электроника");
        techno.setItems(electric);

        ArrayList<ExpandableCategory> list = new ArrayList<>();
        list.add(kitchen);
        list.add(baby);
        list.add(techno);
        return list;
    }
}
