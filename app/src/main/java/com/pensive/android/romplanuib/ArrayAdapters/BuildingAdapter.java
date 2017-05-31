package com.pensive.android.romplanuib.ArrayAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pensive.android.romplanuib.RoomsActivity;
import com.pensive.android.romplanuib.R;
import com.pensive.android.romplanuib.models.Building;
import com.pensive.android.romplanuib.util.FontController;

import java.util.List;

/**
 * @author Edvard Bjørgen
 * @version 1.0
 */
public class BuildingAdapter extends ArrayAdapter<Building> {


    LayoutInflater inflater;
    Context context;
    int textViewResourceId;
    List<Building> buildings;
    FontController fc = new FontController();
    Typeface bebasFont;


    public BuildingAdapter(Context context, int textViewResourceId, List<Building> buildings) {
        super(context, textViewResourceId, buildings);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.textViewResourceId = textViewResourceId;
        this.buildings = buildings;
        this.bebasFont = fc.getTypeface(getContext(), "bebas_neue.ttf");

    }

    public int getCount() {
        return buildings.size();
    }
    public Building getItem(int position) {
        return buildings.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row = convertView;
        BuildingHolder holder = null;

        if (row == null) {
            holder = new BuildingHolder();
            row = inflater.inflate(textViewResourceId, parent, false);


            holder.buildCode = (TextView) row.findViewById(R.id.building_code);
            holder.buildText = (TextView) row.findViewById(R.id.building_name);
            row.setTag(holder);
        } else {

            holder = (BuildingHolder)row.getTag();
        }

        Building uibBuilding = buildings.get(position);
        holder.buildCode.setTypeface(bebasFont);
        holder.buildText.setText(uibBuilding.getName());
        holder.buildCode.setText(uibBuilding.getBuildingAcronym());


        row.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //Toast.makeText(context, getItem(position).getListOfRooms().get(0).getCode(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, RoomsActivity.class);
                i.putExtra("building", getItem(position));
                context.startActivity(i);

            }
            });

        return row;
    }

    private class BuildingHolder
    {
        TextView buildText;
        TextView buildCode;
    }
}
