package com.nibrasco.aghnam.Model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.nibrasco.aghnam.R;

import java.util.*;

public class Cart {

    public static class Lists
    {
        public static class Weight
        {
            Float Price;
            String Name;

            public Weight(Float price, String name) {
                Price = price;
                Name = name;
            }
        }
        public static class Slicing{
            String Name;

            public Slicing(String name){
                Name = name;
            }
        }
        public static class Category{
            String Name;

            public Category(String name){
                Name = name;
            }
        }
        public static class Packaging{
            String Name;
            Float Price;
            public Packaging(Float price, String name){
                Price = price;
                Name = name;
            }
        }

        private static ArrayList<Weight> Weights;
        private static ArrayList<Slicing> Slicings;
        private static ArrayList<Category> Categories;
        private static ArrayList<Packaging> Packages;

        public static String GetWeightName(eCategory Category, int index) {
            Weights = GetWeights(Category);
            if(index >= 0 && index < Weights.size())
                return Weights.get(index).Name;
            return "NaN";
        }
        public static float GetWeightPrice(eCategory Category, int index) {
            Weights = GetWeights(Category);
            if (index >= 0 && index < Weights.size())
                return Weights.get(index).Price;
            return -1;
        }
        public static ArrayList<Weight> GetWeights(eCategory Category){
            Weights = new ArrayList<>();
            switch (Category){
                case HalfSheep:
                    Weights.add( new Weight(660.0f, "نصف نعيمي"));
                    break;
                case Sheep:
                    Weights.add( new Weight(950.0f, "لباني 9-13 كيلو"));
                    Weights.add( new Weight(1030.0f, "صغير 12-15 كيلو"));
                    Weights.add( new Weight(1120.0f, "وسط 15-17 كيلو"));
                    Weights.add( new Weight(1210.0f, "جدع وسط 17-20 كيلو"));
                    Weights.add( new Weight(1290.0f, "جدع طيب 20-25 كيلو"));
                    Weights.add( new Weight(1370.0f, "جدع ناهي 25-30 كيلو"));
                    break;
                case Goat:
                    Weights.add( new Weight(850.0f, "صغير"));
                    Weights.add( new Weight(920.0f, "وسط"));
                    Weights.add( new Weight(990.0f, "كبير"));
                    break;
                case Camel:
                    Weights.add( new Weight(400.0f, "5 كيلو"));
                    Weights.add( new Weight(600.0f, "10 كيلو"));
                    Weights.add( new Weight(800.0f, "15 كيلو"));
                    Weights.add( new Weight(950.0f, "20 كيلو"));
                    break;
                case GroundMeat:
                    Weights.add( new Weight(400.0f, "مفروم 5 كيلو"));
                    Weights.add( new Weight(430.0f, "مفروم 6 كيلو"));
                    break;
                default:
                    break;
            }
            return Weights;
        }
        public static ArrayList<String> GetWeightNames(eCategory Category) {
            ArrayList<String> list = new ArrayList<>();
            Weights = GetWeights(Category);
            for (Weight w:
                 Weights) {
                list.add(w.Name);
            }
            return list;
        }

        public static ArrayList<String> GetSlicingNames(){
            ArrayList<String> list = new ArrayList<>();
            for (Slicing s:
                 GetSlicings()) {
                list.add(s.Name);
            }
            return list;
        }
        public static String GetSlicingName(int index){
            return GetSlicingNames().get(index);
        }
        public static ArrayList<Slicing> GetSlicings(){
            Slicings = new ArrayList<Slicing>();

            Slicings.add(new Slicing("ثلاجة"));
            Slicings.add(new Slicing("أنصاف"));
            Slicings.add(new Slicing("أرباع"));
            Slicings.add(new Slicing("كامل"));

            return Slicings;
        }

        public static ArrayList<String> GetCategoryNames(){
            ArrayList<String> list = new ArrayList<>();
            for (Category s:
                    GetCategories()) {
                list.add(s.Name);
            }
            return list;
        }
        public static String GetCategoryName(int index){
            return GetCategoryNames().get(index);
        }
        public static ArrayList<Category> GetCategories(){
            Categories = new ArrayList<Category>();

            Categories.add(new Category("نعيمي"));
            Categories.add(new Category("عجل بلدي"));
            Categories.add(new Category("تيس عارضي"));
            Categories.add(new Category("حاشي بلدي"));
            Categories.add(new Category("نصف نعيمي"));

            return Categories;
        }

        public static ArrayList<String> GetPackagingNames(){
            ArrayList<String> list = new ArrayList<>();
            for (Packaging p:
                    GetPackages()) {
                list.add(p.Name);
            }
            return list;
        }
        public static String GetPackagingName(int index){
            return GetPackagingNames().get(index);
        }
        public static ArrayList<Packaging> GetPackages(){
            Packages = new ArrayList<>();

            Packages.add(new Packaging(0.0f, "بدون"));
            Packages.add(new Packaging(30.0f,"كيس"));
            Packages.add(new Packaging(40.0f, "أطباق"));

            return Packages;
        }
        public static float GetPackagingPrice(int index) {
            Packages = GetPackages();
            if (index >= 0 && index < Packages.size())
                return Packages.get(index).Price;
            return -1;
        }
    }

    public enum eTime {
        Noon(0),
        AfterNoon(1),
        Evening(2);
        private final int value;
        eTime(int value) {
            this.value = value;
        }

        public int Value() {
            return value;
        }
        public static eTime Get(int value){
            for(eTime t : values()){
                if(t.value == value) return t;
            }
            return Noon;
        }
    }
    public enum eSlicing {
        Fridge(0),
        Halfs(1),
        Quarters(2),
        Whole(3);
        private final int value;
        eSlicing(int value) {
            this.value = value;
        }

        public int Value() {
            return value;
        }
        public static eSlicing Get(int value){
            for(eSlicing t : values()){
                if(t.value == value) return t;
            }
            return Fridge;
        }
    }
    public enum ePackaging {
        None(0),
        Bags(1),
        Plates(2);
        private final int value;
        ePackaging(int value) {
            this.value = value;
        }

        public int Value() {
            return value;
        }
        public static ePackaging Get(int value){
            for(ePackaging t : values()){
                if(t.value == value) return t;
            }
            return None;
        }
    }
    public enum eCategory {

        None(-1),
        Sheep(R.drawable.naeme),
        //Lamb(R.drawable.hree),
        Goat(R.drawable.tais),
        GroundMeat(R.drawable.ajl),
        Camel(R.drawable.hashe),
        HalfSheep(R.drawable.half_sheep);
        private final int value;
        eCategory(int value) {
            this.value = value;
        }

        public int Value() {
            return value;
        }
        public static eCategory Get(int value){
            for(eCategory t : values()){
                if(t.value == value) return t;
            }
            return Sheep;
        }

        public int At() {
            switch (this){
                case Sheep:
                    return 0;
                case Goat:
                    return 1;
                case GroundMeat:
                    return 2;
                case Camel:
                    return 3;
                case HalfSheep:
                    return 4;
            }
            return -1;
        }
    }


    public static class Item {

        private int id = 0;
        private eCategory Category= eCategory.None;
        private int Quantity = 0;
        private boolean Intestine = false;
        private String Notes = "";
        private eSlicing Slicing = eSlicing.Fridge;
        private ePackaging Packaging = ePackaging.None;
        private int Weight = -1;
        private float Total = 0.0f;


        public int getId() {
            return id;
        }
        public void setId(int Id) {
            id = Id;
        }
        public eCategory getCategory() {
            return Category;
        }

        public void setCategory(int category) {
            Category = eCategory.Get(category);
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }

        public boolean isIntestine() {
            return Intestine;
        }

        public void setIntestine(boolean intestine) {
            Intestine = intestine;
        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String notes) {
            Notes = notes;
        }

        public eSlicing getSlicing() {
            return Slicing;
        }

        public void setSlicing(int slicing) {
            Slicing = eSlicing.Get(slicing);
        }

        public ePackaging getPackaging() {
            return Packaging;
        }

        public void setPackaging(int packaging) {
            Packaging = ePackaging.Get(packaging);
        }

        public int getWeight() {
            return Weight;
        }

        public void setWeight(int weightIndex) {
            Weight = weightIndex;
        }

        public float getTotal() {
            return (Total =  Lists.GetWeightPrice(Category, Weight)
                    + Lists.GetPackagingPrice(Packaging.Value()))
                    * Quantity;
        }

        public void setTotal(float total) {
            Total = total;
        }

        public float getDefaultPrice()
        {
            switch (Category)
            {
                case None:
                    return 0.0f;
                case Sheep:
                    return 1030.0f;
                case HalfSheep:
                    return 660.0f;
                case Goat:
                    return 990.0f;
                case GroundMeat:
                case Camel:
                    return 400.0f;
                    default:
                        return 0.0f;
            }
        }

        public Item() {

        }

        public Item(DataSnapshot itemSnap)
        {
            id = Integer.parseInt(itemSnap.getKey());
            Category = eCategory.Get(Integer.parseInt(itemSnap.child("Category").getValue().toString()));
            Notes = itemSnap.child("Notes").getValue(String.class);
            Total = Float.parseFloat(itemSnap.child("Total").getValue().toString());
            Intestine = (Integer.parseInt(itemSnap.child("Intestine").getValue().toString()) != 0);
            Packaging  = ePackaging.Get(Integer.parseInt(itemSnap.child("Packaging").getValue().toString()));
            Quantity = Integer.parseInt(itemSnap.child("Quantity").getValue().toString());
            Slicing = eSlicing.Get(Integer.parseInt(itemSnap.child("Slicing").getValue().toString()));
            Weight = Integer.parseInt(itemSnap.child("Weight").getValue().toString());
        }
        public void MapToDbRef(DatabaseReference itemRef)
        {
            itemRef.child("Category").setValue(Category.value);
            itemRef.child("Intestine").setValue(Intestine ? 1 : 0);
            itemRef.child("Notes").setValue(Notes);
            itemRef.child("Packaging").setValue(Packaging.value);
            itemRef.child("Quantity").setValue(Quantity);
            itemRef.child("Slicing").setValue(Slicing.value);
            itemRef.child("Total").setValue(Total);
            itemRef.child("Weight").setValue(Weight);
        }

        public String ToString()
        {
            String str = "\n\tالإختيار:" + Lists.GetCategoryName(Category.At())
                    + "\n\tالكمية:" +  Quantity
                    + "\n\tالتعليب:" + Lists.GetPackagingPrice(Packaging.value)
                    + "\n\tالتقطيع:" + Lists.GetSlicingName(Slicing.value)
                    + "\n\tالحجم:" + Lists.GetWeightName(Category, Weight)
                    + "\n\tالكرش والمصارين:" + (Intestine ? "نعم" : "لا")
                    + "\n\tNotes : " + Notes
                    + "\n";
            return str;
        }


    }

    private eTime TimeOfDelivery;
    private String Address;
    private String BankAccount;
    private List<Item> Items;

    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String bankAccount) {
        BankAccount = bankAccount;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address)
    {
        Address = address;
    }

    public eTime getTimeOfDelivery() {
        return TimeOfDelivery;
    }

    public void setTimeOfDelivery(int TimeOfDelivery) {
        this.TimeOfDelivery = eTime.Get(TimeOfDelivery);
    }

    public int GetCount()
    {
        return Items.size();
    }
    public Item GetItem(int index)
    {
        return Items.get(index);
    }
    public void AddItem(Item item)
    {
        Items.add(item);
    }
    public void RemoveItem(int index){
        Items.remove(index);
    }
    public void RemoveItem(Item item)
    {
        Items.remove(item);
    }
    public void ModifyItem(int index, Item newItem)
    {
        Items.set(index, newItem);
    }
    public List<Item> Items(){return Items;}

    public Cart() {
        Address = "";
        TimeOfDelivery = eTime.Noon;
        BankAccount = "0";
        Items = new ArrayList<Item>();
    }

    public Cart(String address) {
        Address = address;
        TimeOfDelivery = eTime.Noon;
        BankAccount = "0";
        Items = new ArrayList<Item>();
    }

    /*
    public Cart(String address, ArrayList<Item> items) {
        Address = address;
        Items = items;
    }
    */
    public Cart(DataSnapshot cart) {
        Address = cart.hasChild("Address")? cart.child("Address").getValue(String.class) : "";
        TimeOfDelivery = cart.hasChild("TimeOfDelivery")? eTime.Get(Integer.parseInt(cart.child("TimeOfDelivery").getValue().toString())) : eTime.Noon;
        //BankAccount = cart.child("BankAccount").getValue().toString();
        Items = new ArrayList<>();
        if(cart.child("Items").exists() || cart.child("Items").hasChildren())
        {
            DataSnapshot itemsSnap = cart.child("Items");

            for(DataSnapshot itemSnap : itemsSnap.getChildren())
            {
                if(itemSnap.hasChildren())
                    Items.add(new Item(itemSnap));
            }
        }
    }

    public void MapToDbRef(DatabaseReference cartRef) {
        cartRef.child("Address").setValue(Address);
        cartRef.child("TimeOfDelivery").setValue(TimeOfDelivery.Value());
        cartRef.child("BankAccount").setValue(BankAccount);
        if(Items.size() > 0){
            DatabaseReference itemsRef = cartRef.child("Items");
            for (Item item : Items) {
                item.MapToDbRef(itemsRef.child(Integer.toString(item.id)));
            }
        }

    }

    public float GetTotal() {
        if (Items.size() > 0)
        {
            float t = 0.0f;
            for (Item i:
                 Items) {
                t += i.getTotal();
            }
            return t;
        }
        return 0.0f;
    }

    public String ToString(User usr) {
        String obj = "إسم الزبون:" + usr.getName() +
                "\n\tرقم الهاتف:" + usr.getPhone() +
                "\n\tالعنوان:" + Address +
                "\n\n\tوقت التوصيل:" + TimeOfDelivery +
                "\n\tالطلبات(العدد=" + Items.size() + "):\n";
        for(Item item : Items)
        {
            obj += item.ToString();
        }
        obj += "\nالمجموع:" + GetTotal();
        return obj;
    }

}
