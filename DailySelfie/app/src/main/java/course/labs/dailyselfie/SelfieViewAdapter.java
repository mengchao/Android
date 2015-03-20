package course.labs.dailyselfie;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SelfieViewAdapter extends BaseAdapter {

	private ArrayList<SelfieRecord> list = new ArrayList<SelfieRecord>();
	private static LayoutInflater inflater = null;
	private Context mContext;

	public SelfieViewAdapter(Context context) {
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View newView = convertView;
		ViewHolder holder;

        SelfieRecord curr = list.get(position);

		if (null == convertView) {
			holder = new ViewHolder();
			newView = inflater
					.inflate(R.layout.activity_daily_selfie, parent, false);
			holder.selfie = (ImageView) newView.findViewById(R.id.selfie);
			holder.name = (TextView) newView.findViewById(R.id.name);
			newView.setTag(holder);

		} else {
			holder = (ViewHolder) newView.getTag();
		}

		holder.selfie.setImageBitmap(curr.getmBitmap());
		holder.name.setText(curr.getName());

		return newView;
	}

	static class ViewHolder {

		ImageView selfie;
		TextView name;

	}

	public void add(SelfieRecord listItem) {
		list.add(listItem);
		notifyDataSetChanged();
	}

	public ArrayList<SelfieRecord> getList() {
		return list;
	}

	public void removeAllViews() {
		list.clear();
		this.notifyDataSetChanged();
	}
}
