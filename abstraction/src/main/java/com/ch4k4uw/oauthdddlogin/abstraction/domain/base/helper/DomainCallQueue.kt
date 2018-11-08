package com.ch4k4uw.oauthdddlogin.abstraction.domain.base.helper

typealias Func0<T> = ((T) -> Unit, (Throwable) -> Unit) -> Unit
typealias Func1<T, T2> = (T, (T2) -> Unit, (Throwable) -> Unit) -> Unit

class DomainCallQueue<T> {
    @Volatile
    private var param1: T? = null

    @Volatile
    private var queue = arrayListOf<(((Throwable?) -> Unit) -> Unit)>()

    companion object {
        fun <T2> enqueue(func: Func0<in T2>): DomainCallQueue<T2> {
            val result = DomainCallQueue<T2>()

            result.queue.add { next ->
                run {
                    try {
                        func({ r ->
                            result.param1 = r
                            next(null)
                        }, {
                            next(it)
                        })
                    } catch (e: Exception) {
                        next(e)
                    }

                }
            }

            return result

        }

    }

    fun <T2> enqueue(func: Func1<in T, T2>): DomainCallQueue<T2> {
        val result = DomainCallQueue<T2>()

        queue.add {next ->
            run {
                try {
                    func(param1!!, { r ->
                        result.param1 = r
                        next(null)
                    }, {
                        next(it)
                    })
                } catch (e: Exception) {
                    next(e)
                }

            }
        }

        result.queue.addAll(queue)

        return result
    }


    fun exec(success: (T) -> Unit, error: (Throwable) -> Unit) {
        var func: (((Throwable?) -> Unit) -> Unit)? = null
        val exec: (() -> Unit)?
        var nextFunc: (() -> Unit)? = null
        var finalize: (() -> Unit)? = null

        if(queue.size == 0) {
            error(Exception("There are no methods to exec"))
            return
        }

        exec = {
            func!! { err ->
                if(err != null) {
                    error(err)
                } else {
                    nextFunc!!()
                }
            }
        }

        nextFunc = {
            func = if (queue.size > 0) queue.removeAt(0) else null
            if(func == null) {
                finalize!!()
            } else {
                exec()
            }
        }

        finalize = {
            success(param1!!)
        }

        nextFunc()

    }

}