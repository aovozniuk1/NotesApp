package learn.android.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_list_container,NotesFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ищем фрагмент, который сидит в контейнере R.id.cities_container
        Fragment backStackFragment = (Fragment)getSupportFragmentManager()
                .findFragmentById(R.id.notes_list_container);
        // если такой есть, и он является CoatOfArmsFragment
        if(backStackFragment!=null&&backStackFragment instanceof NoteDescriptionFragment){
            //то сэмулируем нажатие кнопки Назад
            onBackPressed();
        }
    }
}