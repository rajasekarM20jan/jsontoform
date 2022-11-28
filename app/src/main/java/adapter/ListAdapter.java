package adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jsontoform.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List objects;
    public ListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view= inflater.inflate(resource,null);

        LinearLayout layout=view.findViewById(R.id.linearLayoutCustom);

        layout.addView((View) objects.get(position));

        return view;
    }
}
