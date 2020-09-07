package hr.goodapp.warsapp.ui.common

/**
 * Base status model object.
 *
 *
 * This status can either be successful or not. In either case, it must be complete to represent
 * these states.
 */
class Resource<T> private constructor(
    val state: State, private val mValue: T?, private val mException: Throwable?
) {

    var isUsed: Boolean = false
        private set

    val exception: Throwable?
        get() {
            isUsed = true
            return mException
        }

    val value: T?
        get() {
            isUsed = true
            return mValue
        }

    companion object {

        /**
         * Creates a successful resource containing a value.
         */
        fun <T> forSuccess(value: T): Resource<T> {
            return Resource(State.SUCCESS, value, null)
        }

        /**
         * Creates a failed resource with an exception.
         */
        fun <T> forFailure(e: Throwable): Resource<T> {
            return Resource(State.FAILURE, null, e)
        }

        /**
         * Creates a resource in the loading status, without a value or an exception.
         */
        fun <T> forLoading(): Resource<T> {
            return Resource(State.LOADING, null, null)
        }
    }
}