import { DependencyList, EffectCallback, useEffect, useRef } from "react";

const useEffectOnMount = (cb: EffectCallback, dependencies: DependencyList | undefined) => {
  const mounted = useRef(false);

  useEffect(() => {
    if (!mounted.current) {
      mounted.current = true;
      return cb();
    }
  }, dependencies);
};

export default useEffectOnMount;
