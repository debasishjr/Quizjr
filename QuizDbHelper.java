package com.debasishjr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.debasishjr.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="Quizjr.db";
    private static final int DATABASE_VERSION=1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized  QuizDbHelper getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Science");
        addCategory(c2);
        Category c3 = new Category("Current_Affairs");
        addCategory(c3);
    }
    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable()
    {
        Question q1 = new Question("What is the size of char variable?",
                "8 bit", "16 bit", "32 bit", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q1);
        Question q2 = new Question("What is the default value of int variable?",
                "0", "0.0", "null", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q2);
        Question q3 = new Question("The pointer which stores always the current active object address is",
                "auto_ptr", "this", "p", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q3);
        Question q4 = new Question(" An exception is __",
                "Runtime error", "Compile time error", "Logical error", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q4);
        Question q5 = new Question("The operator used to access member function of a structure using its object in C++ is",
                ".", "-->", "_*", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q5);
        Question q6 = new Question("What is the return type of the hashCode() method in the Object class?",
                "int", "long", "void",1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q6);
        Question q7 = new Question("Which of the following is a valid declaration?",
                "char ch = '\\utea';", "char ca = 'tea';", "char cr = \\u0223;",1 ,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q7);
        Question q8 = new Question("Evaluate the following Java expression, if x=3, y=5, and z=10:\n" +
                "++z + y - y + z + x++",
                "24", "23", "20", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q8);

        Question q9 = new Question("In C++, Operators sizeof and ?:",
                "Both can be overloaded", "Both can't be overloaded", "Only sizeof can be overloaded", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q9);
        Question q10 = new Question("Linker generates ___ file.",
                "Object code", "Executable code", "Assembly code", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q10);

        Question q11 = new Question("Output: \n" +
                "\n" +
                "#include<stdio.h>\n" +
                "main()\n" +
                "{\n" +
                "   int i = 1;\n" +
                "   while(++i <= 5)\n" +
                "      printf(\"%d \",i++);\n" +
                "}",
                "1 3 5", "2 4", "2 4 6", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q11);
        Question q12 = new Question("Which standard library function can return a pointer to the last occurrence of a character in a string?",
                "stchar()", "strchr()", "strchar()&stchar()", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q12);
        Question q13 = new Question("Which of the following statement shows the correct implementation of nested conditional operation by finding greatest number out of three numbers?",
                "max = a>b ? a>c?a:c:b>c?b:c", "a=b ? c=30;", "a>b : c=30 : c=40;", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q13);
        Question q14 = new Question("Which of the following creates a List of 3 visible items and multiple selections abled?",
                "new List(false, 3)", "new List(3, true)", "new List(true, 3)", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q14);
        Question q15 = new Question("An interface with no fields or methods is known as a ______.",
                "Runnable Interface", "Marker Interface", "Abstract Interface", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q15);

        Question q20 = new Question("Which force is responsible for binding of the nucleus in an atom?",
                " The electromagnetic force", "The strong force", " The gravitational force", 2,
                Question.DIFFICULTY_EASY, Category.Science);
        addQuestion(q20);
        Question q21 = new Question("If the image on the mirror appears shorter, what type of mirror is this?",
                "Plane", "Concave", "Convex", 3,
                Question.DIFFICULTY_EASY, Category.Science);
        addQuestion(q21);
        Question q22 = new Question("One of the following is not a function of bones",
                "protein synthesis", "production of blood cells", "muscle attachment", 1,
                Question.DIFFICULTY_EASY, Category.Science);
        addQuestion(q22);
        Question q23 = new Question("The good sources of iodine are",
                "Fresh vegetables and fruits", "Seeds", "Sea foods", 3,
                Question.DIFFICULTY_EASY, Category.Science);
        addQuestion(q23);
        Question q24 = new Question("Those substances which give hydroxyl ions in water are called",
                "Alkalies", "Base", "Acid", 2,
                Question.DIFFICULTY_EASY, Category.Science);
        addQuestion(q24);
        Question q25 = new Question("The core of an electromagnet is made of soft iron because soft iron has",
                "small susceptibility and small retentivity", " large susceptibility and small retentivity", "small density and large retentivity", 2,
                Question.DIFFICULTY_MEDIUM, Category.Science);
        addQuestion(q25);
        Question q26 = new Question("Two electric bulbs marked 25W – 220V and 100W – 220V are connected in series to a 440Vsupply. Which of the bulbs will fuse?",
                "25 W", "100 W", "Both", 1,
                Question.DIFFICULTY_MEDIUM, Category.Science);
        addQuestion(q26);
        Question q27 = new Question("The anti-viral proteins released by a viral attacked cell are called ",
                " histamines", "pyrogens", " interferons ", 3,
                Question.DIFFICULTY_MEDIUM, Category.Science);
        addQuestion(q27);
        Question q28 = new Question("What is chemical name of Plaster of Paris?",
                "Calcium sulphate", "Calcium oxide", "Sodium silicate", 1,
                Question.DIFFICULTY_MEDIUM, Category.Science);
        addQuestion(q28);
        Question q29 = new Question("The process, used for the preparation of saturated or unsaturated hydrocarbons by the electrolysis of solutions of the alkali salts of aliphatic carboxylic acids, is known as …",
                "Haber Process", "Kolbe Reaction", "Bayer Process", 2,
                Question.DIFFICULTY_MEDIUM, Category.Science);
        addQuestion(q29);
        Question q30 = new Question("1. Bisphenol A is an organic synthetic compound used in food packaging.\n" +
                "\n" +
                "2. Conversion of force nitrogen in atmosphere into nitrates is called as nitrogen fixation.\n" +
                "\n" +
                "Choose the correct answer from the codes given below:",
                "Only 1", "Only 2", "Both", 3,
                Question.DIFFICULTY_HARD, Category.Science);
        addQuestion(q30);
        Question q31 = new Question("Which among the following is used in soda water?",
                "Alum", "Carbon dioxide", "Carbon monoxide", 2,
                Question.DIFFICULTY_HARD, Category.Science);
        addQuestion(q31);
        Question q32 = new Question("The working of the quartz crystal in the watch is based on the",
                "Photoelectric Effect", "Edison Effect", "Piezo - electric Effect", 3,
                Question.DIFFICULTY_HARD, Category.Science);
        addQuestion(q32);
        Question q33 = new Question("Which of the following is also known as HIV factory?",
                "Macrophages", "memory cells", "Mast cells", 1,
                Question.DIFFICULTY_HARD, Category.Science);
        addQuestion(q33);
        Question q34 = new Question("Antigen-antibody complex is formed at the",
                " ‘constant’ region of light and heavy chain", "‘constant’ regions of light chain", " ‘variable’ region of light and heavy chain", 3,
                Question.DIFFICULTY_HARD, Category.Science);
        addQuestion(q34);

        Question q40 = new Question("President Ram Nath Govind has promulgated the Banking Regulation (Amendment) Ordinance, 2020 recently, the ordinance amends which section of Banking regulation act to bring urban and multi state co-op banks under supervision of RBI?",
                "Section 56", "Section 45", "Both", 3,
                Question.DIFFICULTY_EASY, Category.Current_Affairs);
        addQuestion(q40);
        Question q41 = new Question("Government has notified the new Floating Rate Savings Bonds, 2020 (Taxable) Scheme with effect from July 1, 2020. Which organisation will issue these bonds?",
                "Securities and Exchange Board of India", "Reserve Bank of India", "Insurance Regulatory and Development Authority of India", 2,
                Question.DIFFICULTY_EASY, Category.Current_Affairs);
        addQuestion(q41);
        Question q42 = new Question("How many districts will be covered in “Nasha Mukt Bharat: Annual Action Plan (2020-21)” which was launched by Social Justice and Empowerment ministry?",
                "272", "262", "252", 1,
                Question.DIFFICULTY_EASY, Category.Current_Affairs);
        addQuestion(q42);
        Question q43 = new Question("Which is the 1st state in India to launch “Godhan Nyay Yojana” to procure the cow dung from livestock owners?",
                "Maharashtra", "Goa", "Chhattisgarh", 3,
                Question.DIFFICULTY_EASY, Category.Current_Affairs);
        addQuestion(q43);
        Question q44 = new Question("What is the new name of Feroz Shah Kotla ground?",
                "Sheila Dikshit Stadium", "Arun Jaitley Stadium", "Ajit Wadekar Stadium", 2,
                Question.DIFFICULTY_EASY, Category.Current_Affairs);
        addQuestion(q44);

        Question q45 = new Question("Which bank has partnered with PhonePe on the Unified Payments Interface (UPI) multi-bank model?",
                "ICICI Bank", "HDFC Bank", "Axis Bank", 1,
                Question.DIFFICULTY_MEDIUM, Category.Current_Affairs);
        addQuestion(q45);
        Question q46 = new Question("World Bank has sanctioned $500 million to which country for cross country (which includes Indian border) road projects?",
                "Pakistan", "Bangladesh", "China", 2,
                Question.DIFFICULTY_MEDIUM, Category.Current_Affairs);
        addQuestion(q46);
        Question q47 = new Question("Vini Mahajan has become the 1st women chief secretary of which state?",
                "Odisha", "Goa", "Punjab", 3,
                Question.DIFFICULTY_MEDIUM, Category.Current_Affairs);
        addQuestion(q47);
        Question q48 = new Question("Who has won the Australian Open 2020 men's singles title?",
                "Roger Federer", "Novak Djokovic", "Rafel Nadal", 2,
                Question.DIFFICULTY_MEDIUM, Category.Current_Affairs);
        addQuestion(q48);
        Question q49 = new Question("Which University hosted Khelo India 2020?",
                "VIT University", "KIIT University", "Jadhavpur University", 2,
                Question.DIFFICULTY_MEDIUM, Category.Current_Affairs);
        addQuestion(q49);

        Question q50 = new Question("Which is the only Indian city ranked among top 30 list in global start-up ecosystem ranking 2020 released by start-up Genome?",
                "Bengaluru", "Kolkata", "Mumbai", 1,
                Question.DIFFICULTY_HARD, Category.Current_Affairs);
        addQuestion(q50);
        Question q51 = new Question("The space agency from which country set to take its first tourists on space walk in 2023?",
                "India", "China", "Russia", 3,
                Question.DIFFICULTY_HARD, Category.Current_Affairs);
        addQuestion(q51);
        Question q52 = new Question("Find the person who has launched Garib Kalyan Rojgar Abhiyan portal recently?",
                "Nitin Gadkari", "Narendra Singh Tomar", "Prahlad Singh Patel", 2,
                Question.DIFFICULTY_HARD, Category.Current_Affairs);
        addQuestion(q52);
        Question q53 = new Question("Who became the first Indian to win the gold medal at the World Para Swimming Championship 2017?",
                "Kanchanmala Pande", "Kavita Pande", "Kasturba Pande", 1,
                Question.DIFFICULTY_HARD, Category.Current_Affairs);
        addQuestion(q53);
        Question q54 = new Question("Gopi Thonakal created history by becoming first Indian man to win which championship?",
                "Asian Snooker Championship", " Asian Shooting Championship", "Asian Marathon Championship", 3,
                Question.DIFFICULTY_HARD, Category.Current_Affairs);
        addQuestion(q54);


    }
    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }
            while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int categoryID,String difficulty)
    {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
