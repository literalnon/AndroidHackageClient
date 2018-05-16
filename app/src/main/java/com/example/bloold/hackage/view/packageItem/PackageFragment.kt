package com.example.bloold.hackage.view.packageItem


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bloold.hackage.R
import com.example.bloold.hackage.view.model.PackageModel
import com.example.bloold.hackage.view.video.IPackageFragmentPresenter
import com.example.bloold.hackage.view.video.IPackageFragmentView
import kotlinx.android.synthetic.main.fragment_package.*
import services.mobiledev.ru.cheap.navigation.INavigationParent


/**
 * A simple [Fragment] subclass.
 */
class PackageFragment : Fragment(), IPackageFragmentView {

    private val screenComponent = DaggerPackageFragmentScreenComponent.builder().build()
    override var presenter: IPackageFragmentPresenter = screenComponent.getPresenter()

    companion object {
        const val EXTRA_PACKAGE_ID = "EXTRA_PACKAGE_ID"

        fun newInstance(id: String?): PackageFragment {
            return PackageFragment().apply {
                if (id != null) {
                    arguments = Bundle().apply { putString(EXTRA_PACKAGE_ID, id) }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_package, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null && arguments.containsKey(EXTRA_PACKAGE_ID)) {
            presenter.getPackage(arguments.getString(EXTRA_PACKAGE_ID))
        }

        presenter.attachView(this)
    }

    override fun packageInfoIsLoaded(packageModel: PackageModel) {
        tvAuthor.text = packageModel.author ?: "author not found"
        tvSourceRepo.text = packageModel.sourceRepo ?: "sourceRepo not found"
        tvReadme.text = packageModel.readme ?: "readme not found"
        tvMaintainer.text = packageModel.maintainer ?: "maintainer not found"
        tvLicense.text = packageModel.license ?: "license not found"
        tvDownloads.text = packageModel.downloads ?: "downloads not found"
    }

    override fun getNavigationParent(): INavigationParent {
        return activity as INavigationParent
    }

    override fun showLoadingDialog(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
