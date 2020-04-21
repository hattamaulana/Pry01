package ac.id.polinema.owner.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ac.id.polinema.owner.database.UserDao;
import ac.id.polinema.owner.helper.ApiHelper;
import ac.id.polinema.owner.model.TransactionModel;
import ac.id.polinema.owner.model.UserModel;

public class TransactionRepository extends Repository {

    private UserDao userDao;

    private static final int CODE_NEW_ORDER = 1;

    public TransactionRepository(Context context) {
        super(context);
        userDao = database.userDao();
    }

    public LiveData<List<TransactionModel>> getNewOrder() {
        MutableLiveData<List<TransactionModel>> liveData = new MutableLiveData<>();
        service.getNewOrder().enqueue(new ApiHelper.EnQueue<>(response -> {
            Log.d(TAG, "getNewOrder: "+ response.getStatus());
            Log.d(TAG, "getNewOrder: "+ response.getMessage());

            if (response.getStatus() == 200) {
                List<TransactionModel> list = (List<TransactionModel>) response.getData();
                executorService.submit(() -> {
                    userDao.remove();

                    for (TransactionModel transaction: list) {
                        UserModel user = transaction.getUser();
                        userDao.save(user);
                    }
                });

                liveData.postValue(list);
            }
        }));

        return liveData;
    }
}