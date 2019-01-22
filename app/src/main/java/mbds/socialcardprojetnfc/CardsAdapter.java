package mbds.socialcardprojetnfc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.view.LayoutInflater;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CardsAdapter extends BaseAdapter {

    Context context;
    ArrayList<SocialCard> data;

    private static LayoutInflater inflater = null;

    public CardsAdapter(Context context, ArrayList<SocialCard> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.item,null );
        TextView header = (TextView) vi.findViewById(R.id.header);
        TextView fb = (TextView) vi.findViewById(R.id.fb);
        TextView email = (TextView) vi.findViewById(R.id.email);
        TextView insta = (TextView) vi.findViewById(R.id.insta);
        TextView tel = (TextView) vi.findViewById(R.id.tel);
        header.setText("Card "+position);
        fb.setText("Facebook : www.fb.com/"+ data.get(position).getFacebook());
        email.setText("Email : "+ data.get(position).getEmail());
        insta.setText("Instagram : "+ data.get(position).getInstagram());
        tel.setText("Telephone : "+data.get(position).getTelephone());
        fb.setPadding(5,5,5,5);
        email.setPadding(5,5,5,5);
        insta.setPadding(5,5,5,5);
        tel.setPadding(5,5,5,5);

        return vi;
    }
}