package vn.hidalat.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.models.Place;

/**
 * Created by khoavankas on 17/06/2016.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>{
    private static final int FIRST_PAGE = 1;
    private static final int BASIC_ITEM = 100;
    private static final int FOOTER_ITEM = 101;
    private static final String TAG = "PlaceAdapter";
    private Context mContext;
    private ArrayList<Place> mData;

    private boolean mIsLoading = false;
    private LoadMoreListener mLoadMoreListener;
    private int mPageLoaded = FIRST_PAGE;

    public PlaceAdapter(Context context, ArrayList<Place> data, RecyclerView recyclerView) {
        this.mContext = context;
        this.mData = data;
        setupLoadMore(recyclerView);
    }

    public void setOnLoadMoreListener(LoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
    }
    @Override
    public int getItemViewType(int position) {
        return mData.get(position) != null ? BASIC_ITEM : FOOTER_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        ViewHolder viewHolder = null;
        switch (viewType) {
            case BASIC_ITEM:
                itemView = inflater.inflate(R.layout.row_place, parent, false);
                viewHolder = new BasicItemViewHolder(itemView);
                break;
            case FOOTER_ITEM:
                itemView = inflater.inflate(R.layout.row_footer, parent, false);
                viewHolder = new EndlessItemViewHolder(itemView);
                break;
        }

        if (viewHolder == null) {
            throw new IllegalStateException("Invalid type, this type of items " + viewType + " can't be handled");
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case BASIC_ITEM:
                Place place = mData.get(position);
                BasicItemViewHolder basicHolder = (BasicItemViewHolder) holder;
                basicHolder.tvName.setText(place.getName());
                basicHolder.tvAddress.setText(place.getAddress());
                basicHolder.tvDescription.setText(place.getDescription());
                Picasso.with(mContext)
                        .load(place.getThumbnail())
                        .into(basicHolder.imgThumbnail);
                Log.e(TAG, place.getThumbnail());
                break;
            case FOOTER_ITEM:
                EndlessItemViewHolder viewHolder = (EndlessItemViewHolder) holder;
                viewHolder.progressbar.setIndeterminate(true);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public final class EndlessItemViewHolder extends ViewHolder {
        public ProgressBar progressbar;
        public EndlessItemViewHolder(View itemView) {
            super(itemView);
            progressbar = (ProgressBar) itemView.findViewById(R.id.progress);
        }
    }

    public final class BasicItemViewHolder extends ViewHolder {
        public TextView tvName;
        public TextView tvAddress;
        public TextView tvDescription;
        public ImageView imgThumbnail;

        public BasicItemViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvAddress = (TextView) itemView.findViewById(R.id.address);
            tvDescription = (TextView) itemView.findViewById(R.id.description);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }

    public ArrayList<Place> getData() {
        return mData;
    }

    public void setPageLoaded(int pageLoaded) {
        this.mPageLoaded = pageLoaded;
    }

    public void setLoading(boolean isLoading) {
        this.mIsLoading = isLoading;
    }

    private void setupLoadMore(RecyclerView recyclerView){
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                    if (!mIsLoading && (lastVisibleItem + 1 >= totalItemCount)) {
                        Log.e("HistoryAdapter", "onScrolled - END_REACHED LOADING MORE");
                        // End has been reached
                        // Add loading to end of recycler view
                        addItem(null);
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.onLoadMore(mPageLoaded + 1);
                            mIsLoading = true;
                        }
                    }
                }
            });
        }
    }

    private void addItem(Place item) {
        if (mData != null && !mData.contains(item)) {
            mData.add(item);
            notifyItemInserted(mData.size() - 1);
        }
    }

    public interface LoadMoreListener {
        void onLoadMore(int pagePosition);

        void onLoadMoreSuccess(int pagePosition, ArrayList<Place> appendData);

        void onLoadMoreFail(String message);
    }
}
