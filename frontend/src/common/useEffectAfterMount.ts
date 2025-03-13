import { DependencyList, EffectCallback, useEffect, useRef } from "react";

const useEffectAfterMount = (cb: EffectCallback, dependencies: DependencyList | undefined) => {
  const mounted = useRef(false);

  useEffect(() => {
    if (mounted.current) {
      return cb();
    }
    mounted.current = true;
  }, dependencies);
};

export default useEffectAfterMount;
