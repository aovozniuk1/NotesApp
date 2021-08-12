package learn.android.notesapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class NotesFragment extends Fragment {

    private Note currentNote;
    private static String KEY_NOTE = "note";
    private boolean isLandScape;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLandScape = getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;
        if(savedInstanceState!=null){
            currentNote = savedInstanceState.getParcelable(KEY_NOTE);
        }
        if(isLandScape)
            if(currentNote !=null){
                showNoteDescription(currentNote.getName());
            }else{
                showNoteDescription( (getResources().getStringArray(R.array.notes_names)[0]));
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list,container,false);
        LinearLayout linearLayout = (LinearLayout) view;

        String[] cities = getResources().getStringArray(R.array.notes_names);

        for(int i =0;i<cities.length;i++){
            String name = cities[i];
            TextView textView = new TextView(getContext());
            textView.setText(name);
            textView.setTextSize(30);
            linearLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNoteDescription(name);
                }
            });
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_NOTE,currentNote);
        super.onSaveInstanceState(outState);
    }

    private void showNoteDescription(String name) {
        currentNote = new Note(name,
                Arrays.stream(getResources().getStringArray(R.array.notes_names)).filter(e -> e.equals(name)).findFirst().get()
        );
        if (isLandScape) {
            showNoteDescriptionLand();
        }else{ // port
            showNoteDescriptionPort();
        }
    }

    private void showNoteDescriptionPort() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_list_container, NoteDescriptionFragment.newInstance(currentNote))
                .addToBackStack("")
                .commit();
    }

    private void showNoteDescriptionLand() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_description_container, NoteDescriptionFragment.newInstance(currentNote))
                .commit();
    }

}
