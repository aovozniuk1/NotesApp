package learn.android.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NoteDescriptionFragment extends Fragment {

    public static String KEY_NOTE = "note";
    private Note note;

    public static NoteDescriptionFragment newInstance(Note note){
        NoteDescriptionFragment fragment=  new NoteDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTE,note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            note = getArguments().getParcelable(KEY_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_description,container,false);
        TextView name = view.findViewById(R.id.note_name);
        TextView description = view.findViewById(R.id.note_description);
        name.setText(note.getName());
        description.setText(note.getName() + " desc");
        return view;
    }

}
